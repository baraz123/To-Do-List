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
    private int interval;
    private String sessionid;
    private String active;


    private static ArrayList<ArrayList<String>> listFinal = new ArrayList<>();

    public static int getTotalActiveSessions(){
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        System.out.println("Session has been created");
        HttpSession session = httpSessionEvent.getSession();
        ServletContext Atributes = session.getServletContext();
        System.out.println(session + " Created");
        System.out.println("ID= "+ session.getId() + " MaxInactiveInterval=" + session.getMaxInactiveInterval() +Atributes.getAttribute("username"));
        totalActiveSessions++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        System.out.println("Session Destroyed has been called");
        HttpSession session = httpSessionEvent.getSession();
        ServletContext Atributes = session.getServletContext();
        System.out.println(session + " Destroyed");
        System.out.println("ID= "+ session.getId() + " MaxInactiveInterval=" + session.getMaxInactiveInterval() + Atributes.getAttribute("username"));
        totalActiveSessions--;
        ArrayList list = new ArrayList();
        list.add("SessionID: "+session.getId());
        list.add("MaxInactiveInterval: "+Integer.toString(session.getMaxInactiveInterval()));
        if (Atributes.getAttribute("username")!=null) {
            list.add("username: " + Atributes.getAttribute("username").toString());
            list.add("firstname: "+Atributes.getAttribute("firstname").toString());
            list.add("lastname: "+Atributes.getAttribute("lastname").toString());
        }
        list.add("Inactive");
        listFinal.add(list);

    }


    public static ArrayList<ArrayList<String>> getListFinal() {
        return listFinal;
    }
    public static void setListFinal(ArrayList<ArrayList<String>> listFinal) {
        SessionsListener.listFinal = listFinal;
    }

}
