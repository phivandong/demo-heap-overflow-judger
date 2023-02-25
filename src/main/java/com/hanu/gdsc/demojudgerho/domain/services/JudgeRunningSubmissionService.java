package com.hanu.gdsc.demojudgerho.domain.services;

import com.hanu.gdsc.demojudgerho.domain.config.RunningSubmissionConfig;
import com.hanu.gdsc.demojudgerho.domain.models.*;
import com.hanu.gdsc.demojudgerho.domain.repositories.RunningSubmissionRepository;
import com.hanu.gdsc.demojudgerho.domain.repositories.SubmissionRepository;
import com.hanu.gdsc.demojudgerho.domain.repositories.TestCaseRepository;
import com.hanu.gdsc.demojudgerho.domain.vm.VirtualMachine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class JudgeRunningSubmissionService {
    private ThreadPoolExecutor executor;
    private final RunningSubmissionRepository runningSubmissionRepository;
    private final TestCaseRepository testCaseRepository;
    private final SubmissionRepository submissionRepository;
    private final VirtualMachine virtualMachine;
    private final RunningSubmissionConfig runningSubmissionConfig;
    private final AtomicBoolean stopJudge = new AtomicBoolean(false);

    public JudgeRunningSubmissionService(RunningSubmissionRepository runningSubmissionRepository,
                                         TestCaseRepository testCaseRepository,
                                         SubmissionRepository submissionRepository,
                                         VirtualMachine virtualMachine,
                                         RunningSubmissionConfig runningSubmissionConfig) {
        this.runningSubmissionRepository = runningSubmissionRepository;
        this.testCaseRepository = testCaseRepository;
        this.submissionRepository = submissionRepository;
        this.virtualMachine = virtualMachine;
        this.runningSubmissionConfig = runningSubmissionConfig;

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(runningSubmissionConfig.getMaxJudgingThread());
        new Scheduler(runningSubmissionConfig.getScanRateMillis(), new Scheduler.Runner() {
            @Override
            protected void run() throws Throwable {
                process();
            }
        }).start();
    }

    private boolean allThreadsAreActive() {
        return executor.getActiveCount() == runningSubmissionConfig.getMaxJudgingThread();
    }

    private synchronized void process() throws Exception {
        if (stopJudge.get())
            return;
        if (allThreadsAreActive())
            return;
        final RunningSubmission runningSubmission = runningSubmissionRepository.getOne();
        if (runningSubmission != null) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        judgeSubmission(runningSubmission);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    private void judgeSubmission(RunningSubmission runningSubmission) throws IOException, InterruptedException {
        final List<TestCase> testCases = testCaseRepository.getByProblemId(runningSubmission.getProblemId());

        for (int i = 0; i < testCases.size(); i++) {
            final TestCase testCase = testCases.get(i);

            VirtualMachine.RunResult runResult = virtualMachine.run(
                    runningSubmission.getCode(),
                    testCase.getInput(),
                    runningSubmission.getProgrammingLanguage()
            );
            Submission submission = null;

            OutputComparator.CompareResult compareResult = OutputComparator.compare(testCase.getExpectedOutput(), runResult.output());
            submission = Submission.fromRunningSubmission(runningSubmission);
            if (!compareResult.equal) {
                submission.setResult(false);
            } else {
                submission.setResult(true);
            }

            if (i == testCases.size() - 1) {
                saveSubmission(submission, runningSubmission);
                break;
            }
        }
        if (testCases.size() == 0) {
            runningSubmissionRepository.delete(runningSubmission.getId());
        }
    }

    private void saveSubmission(Submission submission,
                                RunningSubmission runningSubmission) {
        runningSubmissionRepository.delete(runningSubmission.getId());
        submissionRepository.save(submission);
    }
}
