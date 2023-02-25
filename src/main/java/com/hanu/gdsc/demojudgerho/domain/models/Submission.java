package com.hanu.gdsc.demojudgerho.domain.models;

import org.bson.types.ObjectId;

import java.util.Date;

public class Submission {
    private ObjectId id;
    private ObjectId problemId;
    private ProgrammingLanguage programmingLanguage;
    private Date submittedAt;
    private String code;
    private ObjectId coderId;
    private boolean result;

    public Submission(ObjectId id, ObjectId problemId, ProgrammingLanguage programmingLanguage, Date submittedAt, String code, ObjectId coderId) {
        this.id = id;
        this.problemId = problemId;
        this.programmingLanguage = programmingLanguage;
        this.submittedAt = submittedAt;
        this.code = code;
        this.coderId = coderId;
    }

    public static Submission fromRunningSubmission(RunningSubmission runningSubmission) {
        return new Submission(
                runningSubmission.getId(),
                runningSubmission.getProblemId(),
                runningSubmission.getProgrammingLanguage(),
                runningSubmission.getSubmittedAt(),
                runningSubmission.getCode(),
                runningSubmission.getCoderId()
        );
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getProblemId() {
        return problemId;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public String getCode() {
        return code;
    }

    public ObjectId getCoderId() {
        return coderId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
