/*
Класс для работы с отношениями между членами семьи
 */
public class Relations {
    enum TreeRelationsType {
        Супруг, Родитель, Ребенок
    }

    enum RelationsType {
        мама, папа, сын, дочь, брат, сестра, дедушка, бабушка, муж, жена, тетя, дядя, внук, внучка, двоюродная_сестра,
        двоюродный_брат
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
