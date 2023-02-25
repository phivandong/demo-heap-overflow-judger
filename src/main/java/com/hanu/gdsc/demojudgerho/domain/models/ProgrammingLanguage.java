package com.hanu.gdsc.demojudgerho.domain.models;

public enum ProgrammingLanguage {
    JAVA, PYTHON, CPLUSPLUS, JAVASCRIPT;

    public static ProgrammingLanguage from(String val) throws Exception {
        switch (val) {
            case "JAVA":
                return JAVA;
            case "PYTHON":
                return PYTHON;
            case "CPLUSPLUS":
                return CPLUSPLUS;
            case "JAVASCRIPT":
                return JAVASCRIPT;
            default:
                return null;
        }
    }
}
