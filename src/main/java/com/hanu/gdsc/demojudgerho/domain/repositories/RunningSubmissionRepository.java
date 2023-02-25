package com.hanu.gdsc.demojudgerho.domain.repositories;

import com.hanu.gdsc.demojudgerho.domain.models.RunningSubmission;
import org.bson.types.ObjectId;

public interface RunningSubmissionRepository {
    void delete(ObjectId id);
    void updateClaimed(RunningSubmission runningSubmission);
    RunningSubmission getOne() throws Exception;
}
