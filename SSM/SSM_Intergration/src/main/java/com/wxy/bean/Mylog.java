package com.wxy.bean;

import java.util.Date;

public class Mylog {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mylog.id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mylog.product_id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    private Long productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mylog.description
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mylog.createtime
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    private Date createtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mylog.id
     *
     * @return the value of mylog.id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mylog.id
     *
     * @param id the value for mylog.id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mylog.product_id
     *
     * @return the value of mylog.product_id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mylog.product_id
     *
     * @param productId the value for mylog.product_id
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mylog.description
     *
     * @return the value of mylog.description
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mylog.description
     *
     * @param description the value for mylog.description
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mylog.createtime
     *
     * @return the value of mylog.createtime
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mylog.createtime
     *
     * @param createtime the value for mylog.createtime
     *
     * @mbggenerated Thu Oct 11 20:16:52 CST 2018
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}