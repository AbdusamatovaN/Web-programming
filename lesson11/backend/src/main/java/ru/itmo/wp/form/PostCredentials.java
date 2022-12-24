package ru.itmo.wp.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostCredentials {
    @NotEmpty
    @Size(min = 2, max = 24)
    private String title;

    @NotEmpty
    @Size(min = 1, max = 60000)
    private String text;

    @NotEmpty
    private String jwt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
