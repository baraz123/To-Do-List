package com.hit.model;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

public class SessionsListener implements HttpSessionListener {


    private static int totalActiveSessions;
    private int interval;
    private String sessionid;
    private String active;


    private static HashMap<String, String> sessionA=new HashMap<>();

    public static HashMap<String, String> getSessionA() {
        return sessionA;
    }

    public static int getTotalActiveSessions(){
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        System.out.println("Session has been created");
        HttpSession session = httpSessionEvent.getSession();
        System.out.println(session + " Created");
        System.out.println("ID= "+ session.getId() + " MaxInactiveInterval=" + session.getMaxInactiveInterval());
        totalActiveSessions++;
        sessionA.put(session.getId(),"Active");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        System.out.println("Session Destroyed has been called");
        HttpSession session = httpSessionEvent.getSession();
        System.out.println(session + " Destroyed");
        System.out.println("ID= "+ session.getId() + " MaxInactiveInterval=" + session.getMaxInactiveInterval());
        totalActiveSessions--;
        sessionA.remove(session.getId());
        sessionA.put(session.getId(),"Inactive");



    }
}
