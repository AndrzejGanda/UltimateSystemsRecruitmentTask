package pl.ganda.recruitmenttask.student;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.ganda.recruitmenttask.exception.NoRecordException;
import pl.ganda.recruitmenttask.student.model.Student;
import pl.ganda.recruitmenttask.student.model.StudentRequestDTO;
import pl.ganda.recruitmenttask.teacher.TeacherRepository;
import pl.ganda.recruitmenttask.teacher.model.Teacher;
import pl.ganda.recruitmenttask.teacher.model.TeacherResponseDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public List<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).getContent();
    }

    @Transactional
    public Optional<Student> getStudentById(long studentId) {
        return studentRepository.findById(studentId);
    }

    @Transactional
    public List<Student> getStudentByNameOrSurname(String studentName, String studentSurname, Pageable pageable) {
        return studentRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(studentName,studentSurname, pageable).getContent();
    }

    public Student addStudent(StudentRequestDTO studentRequest) {
        return studentRepository.save(new Student(studentRequest));
    }

    public Student assignTeacher(long studentId, long teacherId) throws IllegalArgumentException {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        student.addTeacher(teacher);
        return studentRepository.save(student);
    }

    public void deleteStudent(long studentId) throws NoRecordException {
        try {
            studentRepository.deleteById(studentId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NoRecordException("No user with id: " + studentId);
        }
    }

    public Student deleteTeacher(long studentId, long teacherId) throws IllegalArgumentException {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        student.removeTeacher(teacher);
        return studentRepository.save(student);
    }

    public Set<TeacherResponseDTO> getTeachers(long studentId) throws IllegalArgumentException {
        Student student = studentRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        return student.getTeachers().stream().map(TeacherResponseDTO::new).collect(Collectors.toSet());
    }


}
