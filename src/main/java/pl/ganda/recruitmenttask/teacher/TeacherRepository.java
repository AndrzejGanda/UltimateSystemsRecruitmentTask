package pl.ganda.recruitmenttask.teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.ganda.recruitmenttask.teacher.model.Teacher;


@Repository
public interface TeacherRepository extends PagingAndSortingRepository<Teacher, Long> {

    Page<Teacher> findAll(Pageable pageable);
    Page<Teacher> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String searchingName,
                                                                              String searchingSurname,
                                                                              Pageable pageable);
}
