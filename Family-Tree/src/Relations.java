import com.sun.source.tree.Tree;

/*
Класс для работы с отношениями между членами семьи
 */
public class Relations {
    enum TreeRelationsType {
        spouse, parent, child
    }

    enum RelationsType {
        father, mother, brother, sister, son, daughter, cousin,
        grandmother, grandfather, grandson, granddaughter, aunt, uncle,
        husband, wife
    }

    private Person person1;
    private Person person2;
    private TreeRelationsType type;

    Relations(TreeRelationsType type, Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
        this.type = type;
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public TreeRelationsType getType(){
        return type;
    }

    public void setType(TreeRelationsType type) {
        this.type = type;
    }
}
