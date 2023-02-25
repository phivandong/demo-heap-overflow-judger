package com.hanu.gdsc.demojudgerho.domain.repositories;

import com.hanu.gdsc.demojudgerho.domain.models.Submission;

public interface SubmissionRepository {
    void save(Submission submission);
}
