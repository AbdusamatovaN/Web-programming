package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {

    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public void validateCreation(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required");
        }
        if (!article.getTitle().matches("[a-z]+")) {
            throw new ValidationException("Title can contain only lowercase Latin letters");
        }
        if (article.getTitle().length() > 12) {
            throw new ValidationException("Title can't be longer than 12 letters");
        }
        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is required");
        }
    }

    public void create(Article article) {
        articleRepository.save(article);
    }

    public void updateHidden(Article article) {
        articleRepository.updateHidden(article.isHidden(), article.getId());
    }
}
