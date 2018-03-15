package com.nullthinker.mentaldummy.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Used as a Bean for the User in the Application
 * @author Userr
 *
 */
@Entity
@Table(name="user_details")
public class User implements Serializable {

	@Transient
	private static final long serialVersionUID = -6483867797396021610L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@NotNull(message="userid should not be null")
	private int userid;

	@NotNull(message="First name should not be null")
	@Size(min=1,max=30, message="First Name should be between 1 to 30 chars")
	@Column(name="firstname")
	private String firstname;

	@NotNull(message="Last name should not be null")
	@Size(min=1,max=30, message="Last Name should be between 1 to 30 chars")
	@Column(name="lastname")
	private String lastname;

	@NotNull(message="Email should not be null")
	@Size(min=1,max=50, message="Email should be between 1 to 50 chars")
	@Column(name="email")
	private String email;

	@NotNull(message="Gender should not be null")
	@Size(min=4,max=6, message="Gender should be between 4 to 6 chars")
	@Column(name="gender")
	private String gender;

	@NotNull(message="Password should not be null")
	@Size(min=8,max=1000, message="Password should be between 1 to 1000 chars")
	@Column(name="password")
	private String password;
	
	@NotNull
	@Size(max=20)
	@Column(name="user_type")
	private String type;
	
	/**
	 * To get the type of user
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * To set the type of user
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * To get the user first name.
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * To set the user's first name.
	 * @return
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * To get the user's last name.
	 * @return
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * To set the user's last name.
	 * @return
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * To get the user's email.
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * To set the user's email.
	 * @return
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * To get the user's gender.
	 * @return
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * To set the user's gender.
	 * @return
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * To get the user's password.
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * To set the user's password.
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Override toString() to show user details
	 */
	@Override
	public String toString() {
		return "User [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", gender=" + gender
				+ ", password=" + password + "]";
	}
}
