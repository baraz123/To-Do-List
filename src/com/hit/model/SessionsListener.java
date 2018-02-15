package com.hit.model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionsListener implements HttpSessionListener {


    private static int totalActiveSessions;
    private static ArrayList<ArrayList<String>> listFinal = new ArrayList<>();

    /**
     * @return amount of session's activity in real time.
     */
    public static int getTotalActiveSessions() {
        return totalActiveSessions;
    }

    public static ArrayList<ArrayList<String>> getListFinal() {
        return listFinal;
    }

    public static void setListFinal(ArrayList<ArrayList<String>> listFinal) {
        SessionsListener.listFinal = listFinal;
    }

    /**
     * This function been called at the moment session created.
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        ServletContext Atributes = session.getServletContext();
        totalActiveSessions++;
    }

    /**
     * This function been called at the moment session become invalid
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        HttpSession session = httpSessionEvent.getSession();
        ServletContext Atributes = session.getServletContext();
        totalActiveSessions--;
        ArrayList list = new ArrayList();
        list.add("SessionID: " + session.getId());
        list.add("MaxInactiveInterval: " + Integer.toString(session.getMaxInactiveInterval()));
        if (Atributes.getAttribute("username") != null) {
            list.add("username: " + Atributes.getAttribute("username").toString());
            list.add("firstname: " + Atributes.getAttribute("firstname").toString());
            list.add("lastname: " + Atributes.getAttribute("lastname").toString());
        }
        list.add("Inactive");
        listFinal.add(list);

    }

}
