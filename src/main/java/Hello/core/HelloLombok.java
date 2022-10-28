package Hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok he = new HelloLombok();
        he.setName("maeng2");

        System.out.println(he);
    }
}
