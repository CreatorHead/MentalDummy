package com.nullthinker.mentaldummy.beans;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user_confirmation_details")
public class UserConfirmationDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull(message="confirmation ID should not be null")
	@Column(name="confirmation_id")
	private BigInteger confirmationId;
	
	@Column(name="confirmation_token")
	@NotNull(message="Confirmation Token Should not be null")
	private String confirmationToken;
	
	@Column(name="enabled")
	@NotNull(message="Enabled Should not be null")
	private boolean enabled;

	/**
	 * To get the Confirmation ID
	 */
	public BigInteger getConfirmationId() {
		return confirmationId;
	}

	/**
	 * To set the Confirmation ID
	 */
	public void setConfirmationId(BigInteger confirmationId) {
		this.confirmationId = confirmationId;
	}

	/**
	 * To Get the confirmation token
	 * @return
	 */
	public String getConfirmationToken() {
		return confirmationToken;
	}

	/**
	 * to set the confirmation token
	 * @param confirmationToken
	 */
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	/**
	 * To know user account is Enabled
	 * @return
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * To mark the use account is enabled
	 * @param enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserConfirmationDetails [confirmationToken=" + confirmationToken + ", enabled=" + enabled + "]";
	}
	
}
