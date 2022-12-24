package hillel.ua.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Clients {

    private int id;
    private String name;
    private String email;
    private int phone;
    private String about;
    private String age;
}
