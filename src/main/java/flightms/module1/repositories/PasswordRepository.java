package flightms.module1.repositories;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import flightms.module1.Password;

@Component
public interface PasswordRepository extends CrudRepository<Password,Integer>{
	
	//@Query(value="insert into password values(?1,?2)",nativeQuery=true)
	//public void save(BigInteger psdId, String psd);
	
	@Modifying
	@Query(value="update password set password=?1 where id=?2",nativeQuery=true)
	public void changePassword(String password, BigInteger passwordId);

	@Modifying
	@Query(value="delete from password where id=?1",nativeQuery=true)
	public void deleteById(BigInteger psdId);
	


}
