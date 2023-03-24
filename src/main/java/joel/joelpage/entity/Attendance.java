package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id", nullable = false)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime attendTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Attendance(Long id, LocalDateTime attendTime, Employee employee) {
        this.id = id;
        this.attendTime = attendTime;
        this.employee = employee;
    }
}
