package order.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PlaceorderCommand {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
}
