package com.hanu.gdsc.demojudgerho.infrastructure.repositories.submission;

import com.hanu.gdsc.demojudgerho.domain.models.Submission;
import com.hanu.gdsc.demojudgerho.domain.repositories.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubmissionRepositoryImpl implements SubmissionRepository {
    @Autowired
    private SubmissionEntityJPARepository submissionEntityJPARepository;

    @Override
    public void save(Submission submission) {
        submissionEntityJPARepository.save(SubmissionEntity.toEntity(submission));
    }
}
