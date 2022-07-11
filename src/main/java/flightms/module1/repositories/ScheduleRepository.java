package flightms.module1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import flightms.module1.Schedule;
@Component

public interface ScheduleRepository extends CrudRepository<Schedule, Integer>{

}
