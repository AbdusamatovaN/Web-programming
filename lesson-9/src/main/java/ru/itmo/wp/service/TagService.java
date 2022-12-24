package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

@Service
public class TagService {

    private final TagRepository tagRepository;


    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag find(String str) {
        return tagRepository.findByName(str);
    }

    public void save(Tag tag) {
        this.tagRepository.save(tag);
    }
}
