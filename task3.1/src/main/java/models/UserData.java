package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserData {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String age;
    private String salary;
    private String department;

    public UserData(String id, String firstName, String lastName, String email, String age, String salary, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }
}
