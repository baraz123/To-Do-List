package com.hit.model;

import java.util.Date;
import java.util.List;

/**
 * Interface which stores all the methods that being used for To Do List Application
 *
 * @author Bar Azoulay
 */
public interface IToDoListDAO {

    /**
     * This function creating a new Item and collecting all the parameters from the client
     *
     * @param title
     * @param level
     * @param description
     * @param date
     * @param status
     * @param userid
     */
    public void InsertItem(String title, int level, String description, Date date, String status, String userid);

    /**
     * This function get Item from database by id
     *
     * @param id
     * @throws org.hibernate.HibernateException
     */
    public Item GetItem(long id);

    /**
     * This function does two things:
     * 1) send the id to GetItem and gets back the item
     * 2) send the item to DeleteUserItem function to remove the task from database
     *
     * @param id
     */
    public void DeleteItem(long id);

    /**
     * This function gets task parameters of an exist task and update them in the database
     *
     * @param id
     * @param title
     * @param level
     * @param description
     * @param date
     * @param status
     * @throws org.hibernate.HibernateException
     */
    public void UpdateItem(long id, String title, int level, String description, Date date, String status);

    /**
     * pull user from data and using it.(e.g authentication)
     *
     * @param username
     */
    public User GetUser(String username);

    /**
     * Delete User/Item from the list.
     *
     * @param obj
     */
    public void DeleteUserItem(Object obj);

    /**
     * Saves new Item/user in the database
     *
     * @param obj
     */
    public void InsertRegister(Object obj);

    /**
     * Delete exist user from the database.
     *
     * @param username
     */
    public void DeleteUser(String username);

    /**
     * This function is for authentication, get the user from database and check if the password fits to username
     *
     * @param username
     * @param password
     * @return if the function return true then the user and password are matched.
     * otherwise, the function will return false
     */
    public boolean Login(String username, String password);

    /**
     * Insert all the user's details to a User object and send it to InsertRegister function
     *
     * @param username
     * @param firstname
     * @param lastname
     * @param email
     * @param phone
     * @param newpassword
     */
    public void Register(String username, String firstname, String lastname, String email, String phone, String newpassword);

    /**
     * fetch all the items in database.
     *
     * @param userid
     */
    public List<Item> GetItemList(String userid);


}