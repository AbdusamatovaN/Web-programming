package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class LogoutPage extends Page{

    @Override
    public void action(HttpServletRequest request, Map<String, Object> view) {
        long u = getUserId(request);
        removeUser(request);
        Event event = new Event(u, Event.TYPE.LOGOUT);
        eventService.set(event);
        setMessageToSession("Good bye. Hope to see you soon!", request);
        throw new RedirectException("/index");
    }
}
