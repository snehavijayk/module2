package flightms.module1.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import flightms.module1.User;

@Component
public interface UserRepository extends JpaRepository<User, BigInteger>{
	
	@Modifying
	@Query(value="update userdata set user_Type=?1, user_Name=?2, user_Phone=?3, Email=?4 where user_Id=?5", nativeQuery=true)
	public void updateUser(String userType , String userName, BigInteger userPhone, String Email, BigInteger userId);
	
	//if i dont use this overrided method getting error for fetching user data by user id
	@Override
	@Query("select u from User u where u.userId=?1")
	public User getById(BigInteger userId);
	
	@Query(value="select email from userdata u where u.email=?1",nativeQuery=true)
	public String getEmail(String mail);
	
	@Override
	@Modifying
	@Query(value="delete from userdata u where u.user_id=?1 ",nativeQuery=true)
	public void deleteById(BigInteger userid); 
	
	@Query(value="select * from userdata u where u.email=?1",nativeQuery=true)
	public User getUserByMail(String email);
}
