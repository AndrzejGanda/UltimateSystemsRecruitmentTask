package pl.ganda.recruitmenttask.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ganda.recruitmenttask.student.model.StudentResponseDTO;
import pl.ganda.recruitmenttask.teacher.model.Teacher;
import pl.ganda.recruitmenttask.teacher.model.TeacherRequestDTO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> getTeacher(Pageable pageable) {
        return ResponseEntity.ok(teacherService.getTeachers(pageable));
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("teacherId") long teacherId) {
        Optional<Teacher> teacher = teacherService.getTeacherById(teacherId);
        return teacher.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(teacher.get());
    }

    @GetMapping(params = {"name", "surname"})
    public ResponseEntity<List<Teacher>> getTeacherByNameOrSurname(@RequestParam("name") String teacherName,
                                                                   @RequestParam("surname") String teacherSurname,
                                                                   Pageable pageable) {
        List<Teacher> teachers = teacherService.getTeacherByNameOrSurname(teacherName, teacherSurname, pageable);
        return teachers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(teachers);
    }

    @PostMapping()
    public ResponseEntity<Teacher> addTeacher(@RequestBody @Valid TeacherRequestDTO teacherRequest) {
        return ResponseEntity.ok(teacherService.addTeacher(teacherRequest));
    }

    @PostMapping("/{teacherId}/assignstudent/{studentId}")
    public ResponseEntity<Teacher> assignStudent(@PathVariable("studentId") long studentId, @PathVariable("teacherId") long teacherId) {
        return ResponseEntity.ok(teacherService.assignStudent(studentId, teacherId));
    }

    @DeleteMapping("/{teacherId}/deletestudent/{studentId}")
    public ResponseEntity<Teacher> deleteStudent(@PathVariable("studentId") long studentId, @PathVariable("teacherId") long teacherId) {
        return ResponseEntity.ok(teacherService.deleteStudent(studentId, teacherId));
    }

    @DeleteMapping("/{teacherId}/delete")
    public ResponseEntity<Void> deleteTeacher(@PathVariable("teacherId") long teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{teacherId}/students")
    public ResponseEntity<Set<StudentResponseDTO>> getStudentsFromStudent(@PathVariable("teacherId") long teacherId) {
        Set<StudentResponseDTO> students = teacherService.getStudents(teacherId);
        return students.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(students);
    }
}
