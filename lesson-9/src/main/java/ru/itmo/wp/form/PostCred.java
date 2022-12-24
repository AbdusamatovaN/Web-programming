package ru.itmo.wp.form;

import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.service.TagService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class PostCred {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 30)
    @Pattern(regexp = "[a-z]+", message = "Expected lowercase Latin letters")
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 65000)
    private String text;

    @NotNull
    @NotEmpty
    private String tags;

    private TagService tagService;

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public TagService getTagService() {
        return tagService;
    }

    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    public Post toPost(){
        Post post = new Post();
        post.setTitle(title);
        post.setText(text);
        String[] strings = tags.split(" ");
        List<Tag> tagList = new ArrayList<>();
        for (String st : strings) {
            Tag b = tagService.find(st);
            if (b != null){
                tagList.add(b);
            } else {
                Tag tag = new Tag(st);
                tagList.add(tag);
                tagService.save(tag);
            }
        }
        post.setTags(tagList);
        return post;
    }
}
