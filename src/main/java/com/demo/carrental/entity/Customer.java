package com.demo.carrental.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * customer table
 * </p>
 *
 * @author baomidou
 * @since 2022-09-09
 */
@TableName("table_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String firstName;

    private String middleName;

    private String lastName;

    /**
     * email
     */
    private String email;

    private String salt;

    /**
     * encrypted password
     */
    private String password;

    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }
    public String getSalt(){
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", firstName=" + firstName +
            ", middleName=" + middleName +
            ", lastName=" + lastName +
            ", email=" + email +
            ", createTime=" + createTime +
        "}";
    }
}
