package com.hanu.gdsc.demojudgerho.infrastructure.repositories.runningSubmission;

import com.hanu.gdsc.demojudgerho.domain.models.ProgrammingLanguage;
import com.hanu.gdsc.demojudgerho.domain.models.RunningSubmission;
import lombok.*;
import org.bson.types.ObjectId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "running_submission")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RunningSubmissionEntity {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;
    @Column(columnDefinition = "CHAR(36)")
    private String coderId;
    @Column(columnDefinition = "CHAR(36)")
    private String problemId;
    @Column(columnDefinition = "LONGTEXT")
    private String code;
    private String programmingLanguage;
    private Date submittedAt;

    public static RunningSubmissionEntity fromDomain(RunningSubmission runningSubmission) {
        return RunningSubmissionEntity.builder()
                .id(runningSubmission.getId().toString())
                .coderId(runningSubmission.getCoderId().toString())
                .problemId(runningSubmission.getProblemId().toString())
                .code(runningSubmission.getCode())
                .programmingLanguage(runningSubmission.getProgrammingLanguage().toString())
                .submittedAt(new Date())
                .build();
    }

    public static RunningSubmission toDomain(RunningSubmissionEntity runningSubmissionEntity) throws Exception {
        return new RunningSubmission(
                new ObjectId(runningSubmissionEntity.getId()),
                new ObjectId(runningSubmissionEntity.getCoderId()),
                new ObjectId(runningSubmissionEntity.getProblemId()),
                runningSubmissionEntity.getCode(),
                ProgrammingLanguage.from(runningSubmissionEntity.getProgrammingLanguage()),
                runningSubmissionEntity.getSubmittedAt());
    }
}
