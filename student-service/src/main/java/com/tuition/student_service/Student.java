package com.tuition.student_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student {

    @Id
    private String mssv; // Mã số sinh viên là khóa chính
    private String studentName;
}