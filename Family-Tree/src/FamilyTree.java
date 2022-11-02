import java.util.HashMap;
import java.util.Map;

public class FamilyTree {
    private Person root;
    private Map<String, Boolean> visited = new HashMap<>();

    public Person getRoo() {
        return root;
    }

    public void setRoot(Person root) {
        this.root = root;
    }

    private Person.Gender fetchGender(Relations.RelationsType type) {
        if (Relations.RelationsType.mother.equals(type) ||
                Relations.RelationsType.daughter.equals(type) ||
                Relations.RelationsType.wife.equals(type))
            return Person.Gender.female;
        else
            return Person.Gender.male;
    }

    private Relations.TreeRelationsType fetchTreeRelationType(Relations.RelationsType type) {
        if (Relations.RelationsType.mother.equals(type) || Relations.RelationsType.father.equals(type))
            return Relations.TreeRelationsType.child;
        else if (Relations.RelationsType.husband.equals(type) || Relations.RelationsType.wife.equals(type))
            return Relations.TreeRelationsType.spouse;
        else
            return Relations.TreeRelationsType.parent;
    }
}