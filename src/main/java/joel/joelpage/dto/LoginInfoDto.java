package joel.joelpage.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfoDto {

    @NotEmpty
    private String infoId;
    @NotEmpty
    private String infoPw;
    @NotNull
    private int infoCode;
}
