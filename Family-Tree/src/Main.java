import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String args[]) {
        FamilyTree tree = new FamilyTree();
        tree.addPerson("Michael", Relation.RelationType.husband, "Anastasia", Relation.RelationType.wife);
        tree.addPerson("Michael", Relation.RelationType.father, "Marc", Relation.RelationType.son);
        tree.addPerson("Michael", Relation.RelationType.father, "Luka", Relation.RelationType.son);
        tree.addPerson("Michael", Relation.RelationType.father, "Olga", Relation.RelationType.daughter);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
                String inputString = sc.nextLine();
                if (!inputString.isEmpty()) {
                    String[] input = inputString.split(" ");
                    if (input[0].equals("exit")) {
                        sc.close();
                        System.exit(0);
                    }
                    if (input[0].contains("Person")) {
                        String[] person = input[0].split("=");
                        String[] relation = input[1].split("=");
                        String name = person[1];
                        String value = relation[1].toUpperCase();
                        if (value.charAt(value.length() - 1) == 'S') {
                            value = value.substring(0, value.length() - 1);
                        }

                        Relation.RelationType relationType = Relation.RelationType.valueOf(value);
                        switch (relationType) {

                            case father:
                                Optional<Person> father = Optional.ofNullable(tree.fetchFather(name));
                                if (father.isPresent()) {
                                    System.out.println(relation[1] + "=" + tree.fetchFather(name).getName());
                                }
                                break;
                            case mother:
                                Optional<Person> mother = Optional.ofNullable(tree.fetchMother(name));
                                if (mother.isPresent()) {
                                    System.out.println(relation[1] + "=" + tree.fetchMother(name).getName());
                                }
                                break;
                            case brother:
                                System.out.println(relation[1] + "=" + tree.fetchBrothers(name).stream()
                                        .map(Person::getName).collect(Collectors.joining(",")));
                                break;
                            case sister:
                                System.out.println(relation[1] + "=" + tree.fetchSisters(name).stream().map(Person::getName)
                                        .collect(Collectors.joining(",")));
                                break;
                            case son:
                                System.out.println(relation[1] + "=" + tree.fetchSons(name).stream().map(Person::getName)
                                        .collect(Collectors.joining(",")));
                                break;
                            case daughter:
                                System.out.println(relation[1] + "=" + tree.fetchDaughters(name).stream()
                                        .map(Person::getName).collect(Collectors.joining(",")));
                                break;
                            case grandmother:
                                Optional<Person> grandMother = Optional.ofNullable(tree.fetchGrandMother(name));
                                if (grandMother.isPresent()) {
                                    System.out.println(relation[1] + "=" + tree.fetchGrandMother(name).getName());
                                }
                                break;
                            case grandfather:
                                Optional<Person> grandFather = Optional.ofNullable(tree.fetchGrandFather(name));
                                if (grandFather.isPresent()) {
                                    System.out.println(relation[1] + "=" + tree.fetchGrandFather(name).getName());
                                }
                                break;
                            case grandson:
                                System.out.println(relation[1] + "=" + tree.fetchGrandSons(name).stream()
                                        .map(Person::getName).collect(Collectors.joining(",")));
                                break;
                            case granddaughter:
                                System.out.println(relation[1] + "=" + tree.fetchGrandDaughters(name).stream()
                                        .map(Person::getName).collect(Collectors.joining(",")));
                                break;
                            case husband:
                            case wife:
                                Optional<Person> spouse = Optional.ofNullable(tree.fetchSpouse(name));
                                if (spouse.isPresent()) {
                                    System.out.println(relation[1] + "=" + tree.fetchSpouse(name).getName());
                                }
                                break;
                        }
                    } else {
                        String name1 = input[0].split("=")[1];
                        String name2 = input[1].split("=")[1];
                        Relation.RelationType type1 = Relation.RelationType.valueOf(input[0].split("=")[0].toUpperCase());
                        Relation.RelationType type2 = Relation.RelationType.valueOf(input[1].split("=")[0].toUpperCase());
                        tree.addPerson(name1, type1, name2, type2);
                        System.out.println("Welcome, " + name2 + "!");
                    }
                }

            }
        }
    }
