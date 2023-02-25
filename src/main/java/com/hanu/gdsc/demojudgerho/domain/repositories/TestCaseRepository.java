package com.hanu.gdsc.demojudgerho.domain.repositories;

import com.hanu.gdsc.demojudgerho.domain.models.TestCase;
import org.bson.types.ObjectId;

import java.util.List;

public interface TestCaseRepository {
    List<TestCase> getByProblemId(ObjectId problemId);
}
