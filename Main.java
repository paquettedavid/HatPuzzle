// Created By: David Paquette
// 26 June 2016
// Mikey Packet is responsible for this being done.

import java.util.ArrayList;

public class Main {

    PeopleLine peopleLine;

    public Main(int numberOfPeople) {
        peopleLine = new PeopleLine(numberOfPeople);
    }

    public void printPeople() {
        this.peopleLine.getPeople().forEach(Person::printPerson);
    }

    public void verifyHats() {
        Boolean correct = true;
        for (int i = 1; i < this.peopleLine.getPeople().size(); i++) {
            if (!this.peopleLine.getPeople().get(i).getHat().equals(this.peopleLine.getPeople().get(i).getKnownHat())) {
                correct = false;
            }
        }
        if (correct) {
            System.out.println("Everyone knew their hats.");
        } else {
            System.out.println("Someone didn't know their hat.");
        }
    }

    public void learnHats() {
        for (Person person : this.peopleLine.getPeople()) {
            person.learnHat(this.peopleLine);
        }
    }

    public static void main(String[] args) {
        Integer peopleInLine = 10;
        Main app = new Main(peopleInLine);
        app.learnHats();
        app.verifyHats();
        app.printPeople();
    }
}

class Person {
    private Boolean actualHat;
    private Boolean knownHat;
    private Integer numberInLine;

    public Person(Integer numberInLine, Boolean actualHat) {
        this.numberInLine = numberInLine;
        this.actualHat = actualHat;
    }

    public void learnHat(PeopleLine peopleLine) {
        if (this.numberInLine.equals(0)) {
            peopleLine.setIsEvenNumberOfTrueHats(isEven((int) peopleLine.getPeople().stream().filter(
                    person -> !this.equals(person)).filter(person -> person.getHat()).count()));
        } else {
            this.setKnownHat(isEven((int)peopleLine.getPeople().stream().filter(
                    person -> !this.equals(person) && person.numberInLine != 0).filter(
                    person -> person.getHat()).count()) != peopleLine.getIsEvenNumberOfTrueHats()
            );
        }
    }

    public Boolean isEven(Integer number){
        return (number % 2 == 0);
    }

    public Boolean getHat() {
        return this.actualHat;
    }

    public Boolean getKnownHat() {
        return this.knownHat;
    }

    public void setKnownHat(Boolean knownHat) {
        this.knownHat = knownHat;
    }

    public void printPerson() {
        System.out.println("LineNumber:" + this.numberInLine + " ActualHat:" + this.actualHat + " KnownHat:" + this.knownHat);
    }
}

class PeopleLine {
    private Boolean isEvenNumberOfTrueHats;
    ArrayList<Person> people;

    public PeopleLine(Integer numberOfPeople) {
        this.setIsEvenNumberOfTrueHats(false);
        this.people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++)
            this.people.add(new Person(i, Math.random() < 0.5));
    }

    public void setIsEvenNumberOfTrueHats(Boolean isEvenNumberOfTrueHats) {
        this.isEvenNumberOfTrueHats = isEvenNumberOfTrueHats;
    }

    public Boolean getIsEvenNumberOfTrueHats() {
        return this.isEvenNumberOfTrueHats;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }
}
