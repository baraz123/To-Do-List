package com.hit.model;

import java.util.Date;
import java.util.List;

/**
 * Interface which stores all the methods that being used for To Do List Application
 * @author  Bar Azoulay
 * */
public interface IToDoListDAO {

    /**
     *This function creating a new Item and collecting all the parameters from the client
     * @param title
     * @param level
     * @param description
     * @param date
     * @param status
     * @param userid
     * */
    public void InsertItem(String title, int level, String description, Date date,String status,String userid);

    /**
     * This function get Item from database by id
     * @param id
     * @throws org.hibernate.HibernateException
     * */
    public Item GetItem(long id);

    /**
     * This function does two things:
     * 1) send the id to GetItem and gets back the item
     * 2) send the item to DeleteUserItem function to remove the task from database
     * @param id
     * */
    public void DeleteItem(long id);

    /**
     * This function gets task parameters of an exist task and update them in database
     * @param id
     * @param title
     * @param level
     * @param description
     * @param date
     * @param status
     * @throws org.hibernate.HibernateException
     * */
    public void UpdateItem(long id ,String title, int level, String description, Date date,String status);
    public User GetUser(String username);
    public void DeleteUserItem(Object obj);
    public void InsertRegister(Object obj);
    public void DeleteUser (String username);
    public boolean Login (String username, String password);
    public void Register(String username, String firstname, String lastname, String email, String phone, String newpassword);
    public List<Item> GetItemList(String userid);


}