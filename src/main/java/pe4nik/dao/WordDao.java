package pe4nik.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe4nik.entity.Word;

/**
 * Created by Pe4Nik on 21.03.2017.
 */
@Repository
public interface WordDao extends JpaRepository<Word, Long>{
}
