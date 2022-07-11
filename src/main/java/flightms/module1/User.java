package flightms.module1;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="userdata")
public class User {
	
	@NotNull(message="user type should customer or admin")
	private String userType;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private BigInteger userId;
	@NotBlank(message="name cant be empty")
	private String userName;
	@OneToOne
	@NotNull(message="password")
	private Password userPassword;
	@Digits(integer=10,fraction=0,message="phone number should be 10 digits")
	private BigInteger userPhone;
	@Email
	private String email;
	
	//default constructer
	public User() {}
	//parameterised constructor
	public User(String userType, BigInteger userId, String userName, BigInteger userPhone, String email) {
		this.userType = userType;
		this.userId = userId;
		this.userName = userName;
		this.userPhone = userPhone;
		this.email = email;
	}
	//setters
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPhone(BigInteger userPhone) {
		this.userPhone = userPhone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//getters
	public String getUserType() {
		return userType;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public BigInteger getUserPhone() {
		return userPhone;
	}
	public String getEmail() {
		return email;
	}
	public Password getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(Password userPassword) {
		this.userPassword = userPassword;
	}
}
