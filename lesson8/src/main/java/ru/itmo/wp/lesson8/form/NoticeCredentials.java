package ru.itmo.wp.lesson8.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NoticeCredentials {

    @NotNull
    @NotBlank
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
