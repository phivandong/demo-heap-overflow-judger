package com.hanu.gdsc.demojudgerho.infrastructure.repositories.testCase;

import com.hanu.gdsc.demojudgerho.domain.models.TestCase;
import com.hanu.gdsc.demojudgerho.domain.repositories.TestCaseRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TestCaseRepositoryImpl implements TestCaseRepository {
    @Autowired
    private TestCaseJPARepository testCaseJPARepository;

    @Override
    public List<TestCase> getByProblemId(ObjectId problemId) {
        List<TestCaseEntity> testCaseEntities = testCaseJPARepository.findByProblemId(problemId.toString());
        return testCaseEntities.stream().map(testCaseEntity -> TestCaseEntity.toDomain(testCaseEntity)).collect(Collectors.toList());
    }
}
