import java.util.*;

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
                    if (Relation.TreeRelationType.spouse.equals(relation.getType())) {
                        person1 = relation.getPerson2();
                        break;
                    }
                }
                addRelation(relationType1, person1, relationType2, person2);
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
    Person fetchSpouse(String name) {
        Person spouse = null;
        Person person = findPerson(this.root, name);
        for (Relation relation : person.getRelations()) {
            if (Relation.TreeRelationType.spouse.equals(relation.getType())) {
                spouse = relation.getPerson2();
                break;
            }
        }
        return spouse;
    }

    private List<Person> fetchChildren(String name) {
        List<Person> children = new ArrayList<>();
        Person person = findPerson(this.root, name);
        for (Relation relation : person.getRelations()) {
            if (Relation.TreeRelationType.child.equals(relation.getType())) {
                children.add(relation.getPerson2());
            }
        }
        return children;
    }

    private List<Person> fetchParents(String name) {
        List<Person> parents = new ArrayList<>();
        Person person = findPerson(this.root, name);
        for (Relation relation : person.getRelations()) {
            if (Relation.TreeRelationType.child.equals(relation.getType())) {
                parents.add(relation.getPerson2());
            }
        }
        return parents;
    }

    Person fetchFather(String name) {
        Person father = null;
        List<Person> parents = fetchParents(name);
        for (Person person : parents) {
            if (Person.Gender.male.equals(person.getGender()))
                father = person;
        }
        return father;
    }

    Person fetchMother(String name) {
        Person mother = null;
        List<Person> parents = fetchParents(name);
        for (Person person : parents) {
            if (Person.Gender.male.equals(person.getGender()))
                mother = person;
        }
        return mother;
    }

    private List<Person> fetchSiblings(String name) {
        List<Person> siblings = new ArrayList<>();
        Person father = fetchFather(name);
        List<Person> children = fetchChildren(father.getName());
        for (Person person : children) {
            if (!person.getName().equals(name)) {
                siblings.add(person);
            }
        }
        return siblings;
    }

    List<Person> fetchBrothers(String name) {
        List<Person> brothers = new ArrayList<>();
        List<Person> siblings = fetchSiblings(name);
        for (Person person : siblings) {
            if (Person.Gender.male.equals(person.getGender())) {
                brothers.add(person);
            }
        }
        return brothers;
    }

    List<Person> fetchSisters(String name) {
        List<Person> sisters = new ArrayList<>();
        List<Person> siblings = fetchSiblings(name);
        for (Person person : siblings) {
            if (Person.Gender.female.equals(person.getGender())) {
                sisters.add(person);
            }
        }
        return sisters;
    }

    List<Person> fetchSons(String name) {
        List<Person> sons = new ArrayList<>();
        List<Person> children = fetchChildren(name);
        for (Person person : children) {
            if (Person.Gender.male.equals(person.getGender())) {
                sons.add(person);
            }
        }
        return sons;
    }

    List<Person> fetchDaughters(String name) {
        List<Person> daughters = new ArrayList<>();
        List<Person> children = fetchChildren(name);
        for (Person person : children) {
            if (Person.Gender.female.equals(person.getGender())) {
                daughters.add(person);
            }
        }
        return daughters;
    }

    private List<Person> fetchGrandparents(String name) {
        List<Person> grandparents = new ArrayList<>();
        List<Person> parents = fetchParents(name);
        for (Person person : parents) {
            grandparents.addAll(fetchParents(person.getName()));
        }
        return grandparents;
    }

    Person fetchGrandFather(String name) {
        Person granFather = null;
        List<Person> grandParents = fetchGrandparents(name);
        for (Person person : grandParents) {
            if (Person.Gender.male.equals(person.getGender())) {
                granFather = person;
            }
        }
        return granFather;
    }

    Person fetchGrandMother(String name) {
        Person fetchGrandMother = null;
        List<Person> grandParents = fetchGrandparents(name);
        for (Person person : grandParents) {
            if (Person.Gender.male.equals(person.getGender())) {
                fetchGrandMother = person;
            }
        }
        return fetchGrandMother;
    }

    private List<Person> fetchGrandChildren(String name) {
        List<Person> children = fetchChildren(name);
        List<Person> grandChildren = new ArrayList<>();
        for (Person person : children) {
            grandChildren.addAll(fetchChildren(person.getName()));
        }
        return grandChildren;
    }

    List<Person> fetchGrandSons(String name){
        List <Person> grandSons = new ArrayList<>();
        List<Person> grandChildren = fetchGrandChildren(name);
        for( Person person : grandChildren){
            if(Person.Gender.male.equals(person.getGender())){
                grandSons.add(person);
            }
        }
        return grandSons;
    }

    List<Person> fetchGrandDaughters(String name){
        List<Person> grandDaughters = new ArrayList<>();
        List<Person> grandChildren = fetchGrandChildren(name);
        for (Person person : grandChildren){
            if(Person.Gender.female.equals(person.getGender())){
                grandDaughters.add(person);
            }
        }
        return grandDaughters;
    }
}
