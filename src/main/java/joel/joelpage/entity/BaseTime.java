package joel.joelpage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AutoCloseable.class)
@MappedSuperclass
@Getter
public class BaseTime {

    @CreatedDate
    @Column(name = "work_date", updatable = false)
    private LocalDate workDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
