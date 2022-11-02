/*
Класс для работы с отношениями между членами семьи
 */
public class Relations {
    enum TreeRelationsType {
        SPOUSE, PARENT, CHILD
    }

    enum RelationsType {
        FATHER, MOTHER, BROTHER, SISTER, SON, DAUGHTER, COUSIN, GRANDMOTHER, GRANDFATHER, GRANDSON, GRANDDAUGHTER, AUNT, UNCLE, HUSBAND, WIFE
    }

    private Person person1;
    private Person person2;

    Relations(Person person1, Person person2) {
        this.person1 = person1;
        this.person2 = person2;
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
}
