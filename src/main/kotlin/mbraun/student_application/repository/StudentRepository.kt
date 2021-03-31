package mbraun.student_application.repository

import mbraun.student_application.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, Long> {
}