package com.hit.controller;
import com.hit.model.HibernateToDoListDAO;
import com.hit.model.Item;
import com.hit.model.SessionsListener;
import com.hit.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ToDoController", urlPatterns = {"/servlets/*"})
public class ToDoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private long id;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        /**
         * path - all the letters after the word "servlets" in the url. e.g
         */
        String path = request.getPathInfo();
        RequestDispatcher dispatcher = null;
        //System.out.println(path);

        switch (path) {
            /**
             *If the user sign in sometime in the past and he still have a cookie, the user will get "welcome message"
             */
            case "/login.jsp":
                Cookie userCookie[];
                userCookie = request.getCookies();
                String name = null;
                if (userCookie != null) {
                    for (int i = 0; i < userCookie.length; i++) {
                        if (userCookie[i].getName().equals("username")) {
                            name = userCookie[i].getValue();
                            break;
                        }
                    }
                }

                if (name != null) {
                    String message = "Welcome " + name;
                    request.setAttribute("messageW", message);
                }
                dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("/login.jsp");
                break;
            /**
             * Because we getting the items list from database it should be in POST method
             */
            case "/todolist.jsp":
                doPost(request, response);
                break;
            case "/logout.jsp":
                response.sendRedirect("/logout.jsp");
                break;
            case "/Register.jsp":
/*                dispatcher=request.getRequestDispatcher("/Register.jsp");
                dispatcher.forward(request,response);*/
                response.sendRedirect("/Register.jsp");
                break;
            /**
             * edit case: taking all the parameters from the old item to edit page.
             */
            case "/edit.jsp":
                setId(Long.parseLong(request.getParameter("id")));
                String title = request.getParameter("title");
                String desc = request.getParameter("desc");
                String imp = request.getParameter("imp");
                String date = request.getParameter("date");
                request.setAttribute("title", title);
                request.setAttribute("desc", desc);
                request.setAttribute("imp", imp);
                request.setAttribute("date", date);
                dispatcher = request.getRequestDispatcher("/edit.jsp");
                dispatcher.forward(request, response);
                break;
            /**
             * * In delete action there's interaction with database
             */
            case "/delete.jsp":
                doPost(request, response);
                break;

            /**
             * Administrator - monitoring Session's activity by SessionsListener class
             */
            case "/administrator.jsp":
                ArrayList<ArrayList<String>> sessions;
                int activeusers = SessionsListener.getTotalActiveSessions();
                sessions = SessionsListener.getListFinal();
                request.setAttribute("active", activeusers);
                request.setAttribute("sessions", sessions);
                dispatcher = request.getRequestDispatcher("/administrator.jsp");
                dispatcher.forward(request, response);
                break;
            default:

        }
    }

    /*-----------------------------------------------------------------------------------------------------------------*/
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        RequestDispatcher dispatcher = null;
        //System.out.println(path + "post");
        switch (path) {

            /**
             * authentication - confirm certification details with database
             */
            case "/authentication.jsp":
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                boolean res = HibernateToDoListDAO.getInstance().Login(user, pass);
                /**
                 ** If the user exists in the system we will open a session with a session attribute of his details
                 */
                if (res) {
                    HttpSession session = request.getSession(true);
                    System.out.println("certification confirmed");
                    ArrayList<String> sessionCreated = new ArrayList<>();
                    ServletContext app = session.getServletContext();
                    User Existuser = HibernateToDoListDAO.getInstance().GetUser(user);
                    session.setAttribute("username", Existuser.getUserName());
                    app.setAttribute("username", Existuser.getUserName());
                    app.setAttribute("firstname", Existuser.getFirstname());
                    app.setAttribute("lastname", Existuser.getLastname());
                    session.setMaxInactiveInterval(30 * 60);
                    /**
                     * Session has been created so adding it to administrator list
                     */
                    sessionCreated.add("SessionID: " + session.getId());
                    sessionCreated.add("MaxInactiveInterval: " + Integer.toString(session.getMaxInactiveInterval()));
                    sessionCreated.add("username: " + app.getAttribute("username").toString());
                    sessionCreated.add("firstname: " + app.getAttribute("firstname").toString());
                    sessionCreated.add("lastname: " + app.getAttribute("lastname").toString());
                    sessionCreated.add("Start Activity: " + session.getCreationTime());
                    SessionsListener.getListFinal().add(sessionCreated);

                    Cookie username = new Cookie("username", user);
                    username.setMaxAge(6000);
                    response.addCookie(username);
                    if (user.equals("admin") && pass.equals("admin1234")) {
                        response.sendRedirect("administrator.jsp");
                    } else response.sendRedirect("/servlets/todolist.jsp");

                } else {
                    String message = "Not a valid Username/Password";
                    request.setAttribute("excmessage", message);
                    dispatcher = request.getRequestDispatcher("/exception.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            /**
             * Creating a new user.
             * Gets all the parameters from the jsp page using getParameter method
             */
            case "/login.jsp":
                String username = request.getParameter("username");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String passR = request.getParameter("password");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String message;
                User u = HibernateToDoListDAO.getInstance().GetUser(username);
                /**
                 * If the username already exists in the system we'll get userExist alert. otherwise, creating a new user.
                 */

                if (u == null) {
                    HibernateToDoListDAO.getInstance().Register(username, firstname, lastname, email, phone, passR);
                    message = "New User created successfully";
                    Cookie cookie = new Cookie("username", username);
                    cookie.setMaxAge(6000);
                    response.addCookie(cookie);
                    dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                } else {
                    message = "User is already Exists";
                    request.setAttribute("messageR", message);
                    dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                }
                /**
                 * Creating a new item to the list.
                 */
                break;
            case "/create.jsp":
                HttpSession session = request.getSession(true);
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String importance = request.getParameter("levelofimportance");
                String date = request.getParameter("date");

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
                Date startDate = null;
                try {
                    startDate = sdf.parse(date);

                } catch (ParseException e) {

                    message = "Something wrong with the date frame";
                    request.setAttribute("excmessage", message);
                    dispatcher = request.getRequestDispatcher("/exception.jsp");
                    dispatcher.forward(request, response);
                    response.sendRedirect("/exception.jsp");
                    e.printStackTrace();
                }

                if (0 <= Integer.parseInt(importance) && Integer.parseInt(importance) <= 2) {
                    HibernateToDoListDAO.getInstance().InsertItem(title, Integer.parseInt(importance), description, startDate, "pending", session.getAttribute("username").toString());
                    message = "A new Item created";
                    request.setAttribute("message", message);
                    dispatcher = request.getRequestDispatcher("/create.jsp");
                    dispatcher.forward(request, response);
                } else {

                    message = "You tried to create illegal item";
                    request.setAttribute("excmessage", message);
                    dispatcher = request.getRequestDispatcher("/exception.jsp");
                    dispatcher.forward(request, response);
                   /* message="Cant create this item";
                    request.setAttribute("message", message);
                    dispatcher=request.getRequestDispatcher("/create.jsp");
                    dispatcher.forward(request,response);*/
                }
                break;
            /**
             *
             */
            case "/todolist.jsp":
                session = request.getSession(true);
                List<Item> items = HibernateToDoListDAO.getInstance().GetItemList(session.getAttribute("username").toString());

                List<Item> it = null;
                if (request.getParameter("param1") != null && items != null) {
                    if (request.getParameter("param1").equals("completed")) {
                        it = new ArrayList<>();
                        for (Item item : items) {
                            if (item.getStatus().equals("completed")) {
                                it.add(item);
                            }
                        }
                    } else if (request.getParameter("param1").equals("pending")) {
                        it = new ArrayList<>();
                        for (Item item : items) {
                            if (item.getStatus().equals("pending")) {
                                it.add(item);
                            }
                        }
                    }
                    request.setAttribute("items", it);
                    dispatcher = request.getRequestDispatcher("/todolist.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("items", items);
                    dispatcher = request.getRequestDispatcher("/todolist.jsp");
                    dispatcher.forward(request, response);
                }

                break;

            case "/edit.jsp":
                long id = getId();
                title = request.getParameter("title");
                String desc = request.getParameter("description");
                String imp = request.getParameter("levelofimportance");
                date = request.getParameter("date");
                String status = request.getParameter("status");

                sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
                startDate = null;
//surround below line with try catch block as below code throws checked exception
                try {
                    startDate = sdf.parse(date);

                } catch (ParseException e) {
                    message = "Something wrong with this update you made";
                    request.setAttribute("excmessage", message);
                    dispatcher = request.getRequestDispatcher("/exception.jsp");
                    dispatcher.forward(request, response);
                    e.printStackTrace();
                }

                HibernateToDoListDAO.getInstance().UpdateItem(id, title, Integer.parseInt(imp), desc, startDate, status);

                message = "Item Updated successfully";
                request.setAttribute("message", message);
                dispatcher = request.getRequestDispatcher("/edit.jsp");
                dispatcher.forward(request, response);
                break;
            case "/delete.jsp":
                setId(Long.parseLong(request.getParameter("id")));
                id = getId();
                HibernateToDoListDAO.getInstance().DeleteItem(id);
                dispatcher = request.getRequestDispatcher("/delete.jsp");
                dispatcher.forward(request, response);
                break;


        }
    }

    /**
     * When the client press update or delete
     * 1) Collecting id parameter when we doing redirect to Update/delete jsp
     * 2) using the id for update/delete the database
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
