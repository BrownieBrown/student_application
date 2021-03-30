package mbraun.student_application.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table
data class Student (
    @Id
    val id: Long = -1,
    val name: String = "",
    val email: String = "",
    val gender: Gender = Gender.OTHER)



