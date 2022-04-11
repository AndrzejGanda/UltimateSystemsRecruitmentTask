package pl.ganda.recruitmenttask.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.ganda.recruitmenttask.student.model.Student;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    Page<Student> findAll(Pageable pageable);
    Page<Student> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String searchingName,
                                                                              String searchingSurname,
                                                                              Pageable pageable);
}
