package com.tuition.student_service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Tự động tiêm (inject) các Repository
public class StudentService {

    private final StudentRepository studentRepository;
    private final TuitionDebtRepository tuitionDebtRepository;

    // Lấy thông tin nợ của 1 sinh viên
    public TuitionDebt getTuitionDebt(String mssv) {
        // Tìm sinh viên, nếu không thấy thì báo lỗi
        studentRepository.findById(mssv)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với MSSV: " + mssv));
        
        // Tìm khoản nợ, nếu không thấy cũng báo lỗi
        return tuitionDebtRepository.findByMssv(mssv)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin nợ cho MSSV: " + mssv));
    }
}