package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "att_id", nullable = false)
    private Long id;

    @Column(name = "att_time", nullable = false)
    private LocalDateTime attendTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public void toAttendTime(LocalDateTime time, Employee employee) {
        this.attendTime = time;
        this.employee = employee;
    }

    public void AttendTimeChange(LocalDateTime time) {
        this.attendTime = time;
    }
}
