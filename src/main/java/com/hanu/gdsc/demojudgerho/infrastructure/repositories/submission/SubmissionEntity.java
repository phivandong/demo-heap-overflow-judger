package com.hanu.gdsc.demojudgerho.infrastructure.repositories.submission;

import com.hanu.gdsc.demojudgerho.domain.models.Submission;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "submission")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(30)")
    private String id;
    @Column(columnDefinition = "VARCHAR(30)")
    private String problemId;
    @Column(columnDefinition = "VARCHAR(30)")
    private String coderId;
    private String programmingLanguage;
    @Column(columnDefinition = "LONGTEXT")
    private String code;
    private String submittedAt;

    public static SubmissionEntity toEntity(Submission submission) {
        return SubmissionEntity.builder()
                .id(submission.getId().toString())
                .problemId(submission.getProblemId().toString())
                .coderId(submission.getCoderId().toString())
                .programmingLanguage(submission.getProgrammingLanguage().toString())
                .code(submission.getCode())
                .submittedAt(submission.getSubmittedAt().toString())
                .build();
    }
}
