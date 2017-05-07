package pe4nik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe4nik.entity.UserData;

/**
 * Created by Pe4Nik on 07.05.2017.
 */
@Repository
public interface UserDataDAO extends JpaRepository<UserData, Long> {
}
