package mbraun.student_application.controller

import mbraun.student_application.model.Gender
import mbraun.student_application.model.Student
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/students")
class StudentController {

    @GetMapping
    fun getAllStudents(): List<Student> {

        return listOf(
            Student(
                1L, "Marco", "marco.braun2013@icloud.com", Gender.MALE
            ),
            Student(
                2L, "Julia", "julia.thum@gmail.com", Gender.FEMALE
            )
        )
    }
}