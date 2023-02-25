package com.hanu.gdsc.demojudgerho.infrastructure.repositories.testCase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseJPARepository extends JpaRepository<TestCaseEntity, String> {
    List<TestCaseEntity> findByProblemId(String problemId);
}
