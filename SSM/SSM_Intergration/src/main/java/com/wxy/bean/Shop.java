package com.wxy.bean;

public class Shop {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.name
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.id
     *
     * @return the value of shop.id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.id
     *
     * @param id the value for shop.id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.name
     *
     * @return the value of shop.name
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.name
     *
     * @param name the value for shop.name
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}