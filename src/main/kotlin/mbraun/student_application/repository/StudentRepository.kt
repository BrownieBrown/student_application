package mbraun.student_application.repository

import mbraun.student_application.model.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface StudentRepository: JpaRepository<Student, Long> {
    @Query(
        "SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Student s WHERE s.email = ?1"
    )
    fun selectExistsEmail(email: String): Boolean
}