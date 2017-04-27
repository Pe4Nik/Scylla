package pe4nik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe4nik.entity.Text;

/**
 * Created by Pe4Nik on 26.03.2017.
 */
@Repository
public interface TextDao extends JpaRepository<Text, Long> {
}
