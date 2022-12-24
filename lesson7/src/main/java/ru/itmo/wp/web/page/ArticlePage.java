package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage {

    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void create(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article = new Article();
        article.setUserId(((User)request.getSession().getAttribute("user")).getId());
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));

        articleService.validateCreation(article);
        articleService.create(article);

        request.getSession().setAttribute("message", "You successfully created Article!");
        throw new RedirectException("/index");
    }
}
