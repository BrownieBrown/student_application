package mbraun.student_application.controller

import lombok.AllArgsConstructor
import mbraun.student_application.model.Gender
import mbraun.student_application.model.Student
import mbraun.student_application.service.StudentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/students")
class StudentController(val studentService: StudentService) {

    @GetMapping
    fun getAllStudents(): List<Student> {
        return studentService.getAllStudents()
    }

    @PostMapping
    fun addStudent(@RequestBody student: Student) {
        studentService.addStudent(student)
    }

    @DeleteMapping("{studentId}")
    fun deleteStudent(@PathVariable ("studentId") studentId: Long) {
        studentService.deleteStudent(studentId)
    }
}