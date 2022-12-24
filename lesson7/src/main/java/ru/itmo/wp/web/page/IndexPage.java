package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.annotation.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** @noinspection unused*/
public class IndexPage {

    ArticleService articleService = new ArticleService();
    UserService userService = new UserService();
    private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
    }

    @Json
    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.findAll());
        view.put("users", userService.findAll());
    }

    private void hide(HttpServletRequest request, Map<String, Object> view){
        try {
            long id = Long.parseLong(request.getParameter("articleId"));

            final Article article = articleService.find(id);
            if (((User) request.getSession().getAttribute("user")).getId() == article.getUserId()) {
                article.setHidden(!article.isHidden());
                articleService.updateHidden(article);
            } else {
                view.put("error", "You are not the owner");
            }
        } catch (NumberFormatException e) {
            view.put("error", "Illegal article id");
        }
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }
}
