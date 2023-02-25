package com.hanu.gdsc.demojudgerho.domain.models;

import org.bson.types.ObjectId;

public class TestCase {
    private ObjectId problemId;
    private String input;
    private String expectedOutput;

    public TestCase(ObjectId problemId, String input, String expectedOutput) {
        this.problemId = problemId;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public ObjectId getProblemId() {
        return problemId;
    }

    public String getInput() {
        return input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }
}
