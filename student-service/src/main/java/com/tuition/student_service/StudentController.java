package com.tuition.student_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students") // Tất cả API trong file này đều bắt đầu bằng /api/v1/students
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // Định nghĩa API: GET http://localhost:8083/api/v1/students/12345/debt
    @GetMapping("/{mssv}/debt")
    public ResponseEntity<TuitionDebt> getStudentDebt(@PathVariable String mssv) {
        TuitionDebt debt = studentService.getTuitionDebt(mssv);
        return ResponseEntity.ok(debt); // Trả về thông tin nợ dưới dạng JSON
    }
}