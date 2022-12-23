package joel.joelpage.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback
class EmployeeTest {

    @Test
    public void BoolDefault() throws Exception {
        //given
        Employee employee = new Employee();

        //when

        //then

    }
}