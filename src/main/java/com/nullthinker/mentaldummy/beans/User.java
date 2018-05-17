package com.nullthinker.mentaldummy.beans;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 * Used as a Bean for the User in the Application
 * @author Userr
 *
 */
@Entity
@Table(name="user_details"
		,uniqueConstraints = {@UniqueConstraint(columnNames="email")}
		)
public class User implements Serializable {

	@Transient
	private static final long serialVersionUID = -6483867797396021610L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="user_id")
	private BigInteger userid;

	@NotNull(message="First name should not be null")
	@Size(min=1,max=30, message="First Name should be between 1 to 30 chars")
	@Column(name="firstname")
	private String firstname;

	@NotNull(message="Last name should not be null")
	@Size(min=1,max=30, message="Last Name should be between 1 to 30 chars")
	@Column(name="lastname")
	private String lastname;

	@NotNull(message="Email should not be null")
	@Email(message = "Please provide a valid e-mail")
	@Size(min=1,max=50, message="Email should be between 1 to 50 chars")
	@Column(name="email")
	private String email;

	@NotNull(message="Gender should not be null")
	@Size(min=4,max=6, message="Gender should be between 4 to 6 chars")
	@Column(name="gender")
	private String gender;

	@NotNull(message="Password should not be null")
	@Size(min=8,max=1000, message="Password should be between 8 to 1000 chars")
	@Column(name="password")
	private String password;
	
	@NotNull
	@Size(max=20)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="user_roles",joinColumns=@JoinColumn(name="user_id"))
	@Column(name="role")
	private List<String> roles;
	
	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	private Date creationDate;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="confirmation_id", referencedColumnName="confirmation_id")
	@NotNull(message="Confirmation Details should not be null")
	private UserConfirmationDetails confirmationDetails;
	
	@Column(name="isActive")
	@NotNull(message="isActive Should not be null")
	private Boolean isActive;
	
	/**
	 * Get the User ID
	 * @return
	 */
	public BigInteger getUserid() {
		return userid;
	}
	
	/**
	 * To Get the user is Active
	 * @return
	 */
	public Boolean getIsActive() {
		return isActive;
	}
	/**
	 * To Set user is Active
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
	
	/**
	 * get the Creation Date
	 * @return
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	
	/**
	 * To Set the creation date
	 * @param creationDate
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Get Roles of a user
	 * @return
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * Set Roles of a user
	 * @param roles
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * To Get the Confirmation Details
	 * @return
	 */
	public UserConfirmationDetails getConfirmationDetails() {
		return confirmationDetails;
	}
	
	/**
	 * To Set the Confirmation Details
	 * @param confirmationDetails
	 */
	public void setConfirmationDetails(UserConfirmationDetails confirmationDetails) {
		this.confirmationDetails = confirmationDetails;
	}
}
