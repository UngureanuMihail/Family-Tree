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
    private List<Relation> relations = new ArrayList<>();

    Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }
    public void SetName(String name) {
        this.name = name;
    }

    public void SetGender(Gender gender) {
        this.gender = gender;
    }
    public void addRelation(Relation relation) {
        relation.add(relation);
    }
    public List<Relation>getRelations(){
        return relations;
    }
    public void setRelations(List<Relation> relations){
        this.relations = relations;
    }
    public Gender getGender(){
        return gender;
    }
    public void setGender(Gender gender){
        this.gender=gender;
    }



}
