package pe4nik.dao;

import org.springframework.stereotype.Repository;
import pe4nik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by Pe4Nik on 24.03.2017.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
