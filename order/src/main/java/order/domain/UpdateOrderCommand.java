package order.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateOrderCommand {

    private Long id;
    private String address;
}
