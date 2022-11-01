import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains the data about persons (members of family)
 */
public class Person {
    ///enumerating genders of family members
    enum Gender {
        male, female
    }

    private String name;
    private Gender gender;

    Person(String name , Gender gender){
        this.name = name;
        this.gender = gender;
    }
    public void SetName(String name){
        this.name = name;
    }
    public void SetGender(Gender gender) {
        this.gender = gender;
    }

}
