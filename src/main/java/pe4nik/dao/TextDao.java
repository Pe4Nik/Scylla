package pe4nik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe4nik.entity.Text;

/**
 * Created by Pe4Nik on 26.03.2017.
 */
public interface TextDao extends JpaRepository<Text, Long> {
}
