package com.hit.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * Item class- item class mapped by Hibernate into a table that called "Items",
 * item presenting a task that the client created.
 *
 * @author Bar Azoulay
 */
@Entity
@Table(name = "Items")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long ID;

    @Column(name = "userid")
    private String userid;

    @Column(name = "importance")
    private String levelOfImp;

    @Column(name = "description")
    private String description;


    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "taskstatus")
    private String status;
    // private int hours, minutes;
    //private String comment;

    /**
     * No-arg constructor is there for Hibernate Reflection.
     */
    public Item() {
    }
    /**
     * Constructor -
     */
/*
    public Item(int level, String description, Date date)
    {
        setLevelOfImp(level);
        this.description=description;
        this.date=date;

    }
*/

    /**
     * @return Task ID from database.
     */
    public long getID() {
        return ID;
    }

    /**
     * ID defined as a GeneratedValue parameter and serve as a key.
     */
    public void setID(long ID) {
        this.ID = ID;
    }

    /**
     * @return Task's date from database.
     */
    public Date getDate() {
        return date;
    }

    /**
     * This function set a new date for a task.
     *
     * @param date - new date parameter.
     */
    public void setDate(Date date) {
        this.date = date;
        System.out.println("setting date " + date);
    }

    /**
     * @return task's description from database.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This function set a new description for a task.
     *
     * @param description - new description parameter.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Parameter from database which indicate how important this task.
     */
    public String getLevelOfImp() {
        return levelOfImp;
    }

    /**
     * this function used for define the importance of the task.
     *
     * @param level - Task's Importance
     */
    public void setLevelOfImp(int level) {
        if (level == 0)
            this.levelOfImp = "Less important";
        else if (level == 1)
            this.levelOfImp = "Important";
        else if (level == 2)
            this.levelOfImp = "Very important";
        else this.levelOfImp = null;
    }

    /**
     * @return task's title from database.
     */
    public String getTitle() {
        return title;
    }

    /**
     * This function used for set a new title for a task.
     *
     * @param title - the new title parameter.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return client's username from database.
     */
    public String getUserid() {
        return userid;
    }

    /**
     * this function used for pair a client to task.
     *
     * @param username Client's username.
     */
    public void setUserid(String username) {
        this.userid = username;
    }

    /**
     * @return task's status from database.
     */
    public String getStatus() {
        return status;
    }

    /**
     * This function used when a client create a new task, task's status will be pending automatically
     * until he change it.
     *
     * @param status - new status parameter.
     */
    public void setStatus(String status) {
        this.status = status;
    }

}

