package joel.joelpage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpWithLoginInfoDto {
    private EmpDto empDto;
    private LoginInfoDto loginInfoDto;
}
