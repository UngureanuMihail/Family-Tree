import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.InputMismatchException;
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

    private Person.Gender fetchGender(Relation.RelationType type) {
        if (Relation.RelationType.mother.equals(type) ||
                Relation.RelationType.daughter.equals(type) ||
                Relation.RelationType.wife.equals(type))
            return Person.Gender.female;
        else
            return Person.Gender.male;
    }

    private Relation.TreeRelationType fetchTreeRelationType(Relation.RelationType type) {
        if (Relation.RelationType.mother.equals(type) || Relation.RelationType.father.equals(type))
            return Relation.TreeRelationType.child;
        else if (Relation.RelationType.husband.equals(type) || Relation.RelationType.wife.equals(type))
            return Relation.TreeRelationType.spouse;
        else
            return Relation.TreeRelationType.parent;
    }

    public void addPerson(String name1, Relation.RelationType type1, String name2, Relation.RelationType type2) {
        Relation.TreeRelationType relationType1 = fetchTreeRelationType(type1);
        Relation.TreeRelationType relationType2 = fetchTreeRelationType(type2);
        Person.Gender gender1 = fetchGender(type1);
        Person.Gender gender2 = fetchGender(type2);
        if (this.root == null) {
            Person person1 = new Person(name1, gender1);
            Person person2 = new Person(name2, gender2);
            this.root = person1;
            addRelation(relationType1, person1, relationType2, person2);
        } else {
            Person person1 = findPerson(this.root, name1);
            if (person1 == null) {
                throw new InputMismatchException();
            }
            Person person2 = new Person(name2, gender2);
            addRelation(relationType1, person1, relationType2, person2);
            if (Relation.TreeRelationType.child.equals(relationType1)) {
                for (Relation relation : person1.getRelations()) {
                    person1 = relation.getPerson2();
                    break;
                }
            }
        }
    }

    private Person findPerson(Person cur, String name) {
        this.visited.put(cur.getName(), Boolean.TRUE);
        if (cur.getName().equals(name)) {
            this.visited.clear();
            return cur;

        } else {
            for (Relation relation : cur.getRelations()) {
                Person person2 = relation.getPerson2();
                if (!visited.containsKey(person2.getName())) {
                    Person person = findPerson(person2, name);
                    if (person != null) {
                        return person;
                    }
                }

            }
        }
        return null;
    }


    private void addRelation(Relation.TreeRelationType type1, Person person1, Relation.TreeRelationType
            type2, Person person2) {
        Relation relation1 = new Relation(type1, person1, person2);
        person1.addRelation(relation1);
        Relation relation2 = new Relation(type2, person2, person1);
        person1.addRelation(relation2);
    }





}