package com.tuition.student_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public TuitionDebt clearDebt(String mssv, Double amount) {
        // 1. Lấy và KHÓA khoản nợ
        TuitionDebt debt = tuitionDebtRepository.findByMssvForUpdate(mssv)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin nợ cho MSSV: " + mssv));

        // 2. Kiểm tra nghiệp vụ (ví dụ: đã trả, hoặc trả lố)
        if (debt.getAmountDue() == 0) {
            throw new RuntimeException("Khoản nợ này đã được thanh toán rồi.");
        }
        if (debt.getAmountDue() < amount) {
            throw new RuntimeException("Số tiền thanh toán lớn hơn số nợ.");
        }
        
        // 3. Gạch nợ (ví dụ: trừ đi số tiền)
        // (Hoặc bạn có thể set = 0 nếu nghiệp vụ yêu cầu trả hết)
        debt.setAmountDue(debt.getAmountDue() - amount);
        if (debt.getAmountDue() == 0) {
            debt.setStatus("PAID");
        }

        // 4. Lưu lại (và nhả lock)
        return tuitionDebtRepository.save(debt);
    }
}