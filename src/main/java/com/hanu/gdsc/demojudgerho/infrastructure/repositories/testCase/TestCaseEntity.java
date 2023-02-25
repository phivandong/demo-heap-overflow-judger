package com.hanu.gdsc.demojudgerho.infrastructure.repositories.testCase;

import com.hanu.gdsc.demojudgerho.domain.models.TestCase;
import lombok.*;
import org.bson.types.ObjectId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_case")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCaseEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    private String problemId;
    @Column(columnDefinition = "LONGTEXT")
    private String input;
    @Column(columnDefinition = "LONGTEXT")
    private String expectedOutput;

    public static TestCase toDomain(TestCaseEntity testCaseEntity) {
        return new TestCase(new ObjectId(testCaseEntity.getProblemId()), testCaseEntity.getInput(), testCaseEntity.getExpectedOutput());
    }
}
