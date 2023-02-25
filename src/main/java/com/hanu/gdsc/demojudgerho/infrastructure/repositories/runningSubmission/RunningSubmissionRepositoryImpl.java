package com.hanu.gdsc.demojudgerho.infrastructure.repositories.runningSubmission;

import com.hanu.gdsc.demojudgerho.domain.models.RunningSubmission;
import com.hanu.gdsc.demojudgerho.domain.repositories.RunningSubmissionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RunningSubmissionRepositoryImpl implements RunningSubmissionRepository {
    @Autowired
    private RunningSubmissionJPARepository runningSubmissionJPARepository;

    @Override
    public void delete(ObjectId id) {
        runningSubmissionJPARepository.deleteById(id.toString());
    }

    @Override
    public void updateClaimed(RunningSubmission runningSubmission) {

    }

    @Override
    public RunningSubmission getOne() throws Exception {
        return RunningSubmissionEntity.toDomain(runningSubmissionJPARepository.get());
    }
}
