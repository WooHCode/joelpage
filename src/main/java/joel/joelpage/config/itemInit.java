package joel.joelpage.config;

import jakarta.annotation.PostConstruct;
import joel.joelpage.entity.Item;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class itemInit {
    private final ItemService itemService;

    @PostConstruct
    public void dbIn(){
        for(int i = 1; i<5; i++){
            
        }
    }

}
