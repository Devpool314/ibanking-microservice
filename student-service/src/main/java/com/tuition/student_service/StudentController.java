package com.tuition.student_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/clear-debt")
    public ResponseEntity<TuitionDebt> clearDebt(@RequestBody ClearDebtRequest clearDebtRequest) {
        TuitionDebt updatedDebt = studentService.clearDebt(
            clearDebtRequest.getMssv(), 
            clearDebtRequest.getAmount()
        );
        return ResponseEntity.ok(updatedDebt);
    }
}
class ClearDebtRequest {
    private String mssv;
    private Double amount;

    // Thêm getters & setters
    public String getMssv() { return mssv; }
    public void setMssv(String mssv) { this.mssv = mssv; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}