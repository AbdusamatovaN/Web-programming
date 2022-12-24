package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class Page {

    protected final UserService userService = new UserService();
    protected final EventService eventService = new EventService();

    public void action(HttpServletRequest request, Map<String, Object> view) {}

    public void before(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }

        putMessage(request, view);
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {}

    protected void setMessageToSession(String message, HttpServletRequest request) {
        request.getSession().setAttribute("message", message);
    }

    protected void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void removeUser(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }

    protected long getUserId(HttpServletRequest request) {
        return ((User) request.getSession().getAttribute("user")).getId();
    }

    protected void setUser(User user, HttpServletRequest request) {
        request.getSession().setAttribute("user", user);
    }
}
