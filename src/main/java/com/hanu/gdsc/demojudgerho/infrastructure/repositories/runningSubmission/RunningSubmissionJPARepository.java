package com.hanu.gdsc.demojudgerho.infrastructure.repositories.runningSubmission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RunningSubmissionJPARepository extends JpaRepository<RunningSubmissionEntity, String> {
    @Query(value = "SELECT * FROM running_submission ORDER BY submitted_at DESC LIMIT 1", nativeQuery = true)
    RunningSubmissionEntity get();
}
