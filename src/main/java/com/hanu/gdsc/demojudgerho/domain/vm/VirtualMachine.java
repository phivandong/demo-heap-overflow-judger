package com.hanu.gdsc.demojudgerho.domain.vm;

import com.hanu.gdsc.demojudgerho.domain.models.ProgrammingLanguage;

import java.io.IOException;

public interface VirtualMachine {
    public static interface RunResult {
        public boolean compilationError();
        public String compilationMessage();
        public boolean stdError();
        public String stdMessage();
        public String output();
    }

    public RunResult run(String code, String input, ProgrammingLanguage programmingLanguage) throws IOException, InterruptedException;
}
