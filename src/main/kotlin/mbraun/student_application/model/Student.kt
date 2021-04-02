package mbraun.student_application.model

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


@Entity
@Table
data class Student (
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(generator = "student_sequence", strategy = GenerationType.SEQUENCE)
    val id: Long = -1,
    @NotBlank
    @Column(nullable = false)
    var name: String = "",
    @Email
    @Column(nullable = false, unique = true)
    var email: String = "",
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var gender: Gender = Gender.OTHER) {

    constructor(name: String, email: String, gender: Gender) : this() {
        this.name = name
        this.email = email
        this.gender = gender
    }
}



