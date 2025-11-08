package  com.tuition.student_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tuition_debts")
@Data
@NoArgsConstructor
public class TuitionDebt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mssv; // Liên kết với sinh viên
    private Double amountDue; // Số tiền cần nộp
    private String status; // Ví dụ: "PENDING", "PAID"
}