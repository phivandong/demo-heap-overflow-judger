package com.hanu.gdsc.demojudgerho.domain.models;

import org.bson.types.ObjectId;

import java.util.Date;

public class RunningSubmission {
    private ObjectId id;
    private ObjectId coderId;
    private ObjectId problemId;
    private String code;
    private ProgrammingLanguage programmingLanguage;
    private Date submittedAt;

    public RunningSubmission(ObjectId id, ObjectId coderId, ObjectId problemId, String code, ProgrammingLanguage programmingLanguage, Date submittedAt) {
        this.id = id;
        this.coderId = coderId;
        this.problemId = problemId;
        this.code = code;
        this.programmingLanguage = programmingLanguage;
        this.submittedAt = submittedAt;
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getCoderId() {
        return coderId;
    }

    public ObjectId getProblemId() {
        return problemId;
    }

    public String getCode() {
        return code;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }
}
