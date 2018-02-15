package com.hit.model;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
    Bar Azoulay 203107461
*/

public class HibernateToDoListDAO implements IToDoListDAO {
    private static HibernateToDoListDAO hiberInstance = new HibernateToDoListDAO();
    private SessionFactory factory;


    private HibernateToDoListDAO() {

        factory = new AnnotationConfiguration().configure().buildSessionFactory();

    }

    public static HibernateToDoListDAO getInstance() {
        if (hiberInstance == null)
            try {
                hiberInstance = new HibernateToDoListDAO();
            } catch (HibernateException H) {
                System.out.println("Having an issue with Hibernate instance");
                H.printStackTrace();
            }

        return hiberInstance;
    }

    public static void main(String[] args) {

        //HibernateToDoListDAO hiber= HibernateToDoListDAO.getInstance();
        //hiber.InsertItem("Title1",1,"need to go to the SuperMarket", new Date());
        //hiber.InsertItem("Title2",2,"need to go to the GYM", new Date());
        // hiber.InsertItem("Title3",0,"need to go to the SuperMarket", new Date());
        //  hiber.Register("Baraz","Bar", "Azoulay", "bar@walla.com", "050030403", "123123123");
        //hiber.Register("babar","1323");
        //hiber.Register("babarr","13233);
        //  boolean res = hiber.Login("Baraz","123123123");
        //boolean re3s = hiber.Login("Ba3raz","123123123");
        // System.out.println(res);
        //System.out.println(re3s);
    }

    public void InsertItem(String title, int level, String description, Date date, String status, String userid) {
        Item item = new Item();
        item.setTitle(title);
        item.setDescription(description);
        item.setDate(date);
        item.setLevelOfImp(level);
        item.setUserid(userid);
        item.setStatus(status);


        InsertRegister(item);


    }

    public Item GetItem(long id) {
        Item item = null;

        Session s = null;
        try {
            s = factory.openSession();
            s.beginTransaction();
            item = (Item) s.get(Item.class, id);
            s.getTransaction().commit();

        } catch (HibernateException e) {

            Transaction tx = s.getTransaction();
            if (tx.isActive()) tx.rollback();
        } finally {
            if (s != null) s.close();
        }
        return item;
    }

    public void UpdateItem(long id, String title, int level, String description, Date date, String status) {

        Item item = GetItem(id);
        item.setTitle(title);
        item.setLevelOfImp(level);
        item.setDescription(description);
        item.setDate(date);
        item.setStatus(status);

        Session s = null;
        try {
            s = factory.openSession();
            s.beginTransaction();
            s.update(item);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            Transaction tx = s.getTransaction();
            if (tx.isActive()) tx.rollback();
        } finally {
            if (s != null) s.close();
        }


    }

    public void DeleteItem(long id) {

        Item item = GetItem(id);
        DeleteUserItem(item);

    }

    public void DeleteUser(String username) {
        User user = GetUser(username);
        DeleteUserItem(user);
    }

    public void Register(String username, String firstname, String lastname, String email, String phone, String newpassword) {
        User user = new User();
        user.setUserName(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(newpassword);

        InsertRegister(user);

    }

    public void InsertRegister(Object obj) {

        Session s = null;
        try {
            s = factory.openSession();
            s.beginTransaction();
            s.save(obj);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            Transaction tx = s.getTransaction();
            if (tx.isActive()) tx.rollback();
        } finally {
            if (s != null) s.close();
        }


    }

    public void DeleteUserItem(Object obj) {

        Session s = null;
        try {
            s = factory.openSession();
            s.beginTransaction();
            s.delete(obj);
            s.getTransaction().commit();
        } catch (HibernateException e) {
            Transaction tx = s.getTransaction();
            if (tx.isActive()) tx.rollback();
        } finally {
            if (s != null) s.close();
        }
    }

    public User GetUser(String username) {
        User user = null;

        Session s = null;
        try {
            s = factory.openSession();
            s.beginTransaction();
            user = (User) s.get(User.class, username);
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Transaction tx = s.getTransaction();
            if (tx.isActive()) tx.rollback();
        } finally {
            if (s != null) s.close();
        }

        return user;
    }

    public boolean Login(String username, String password) {
        User user = new User();
        user = GetUser(username);
        if (user != null) {
            if (user.getPassword().equals(password))
                return true;
        }
        return false;

    }

    public List<Item> GetItemList(String userid) {

        List<Item> list;
        List<Item> listperUser = null;
        Session s = null;
        try {
            s = factory.openSession();
            s.beginTransaction();
            Query q = s.createQuery("from Item");
            list = q.list();
            /*
            * fetch all the data from Mysql and starting to sort the data by User id
            * */
            for (Item item : list) {
                if (item.getUserid().equals(userid)) {
                    if (listperUser != null) {
                        listperUser.add(item);
                    } else {
                        listperUser = new ArrayList<>();
                        listperUser.add(item);
                    }
                }
            }
            s.getTransaction().commit();

        } catch (HibernateException e) {
            Transaction tx = s.getTransaction();
            if (tx.isActive()) tx.rollback();
        } finally {
            if (s != null) s.close();
        }
        return listperUser;
    }
}