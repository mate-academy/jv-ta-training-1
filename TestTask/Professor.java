import java.util.ArrayList;

public class Professor extends Person {

    private String speciality; //Специальность
    private String degree; // Научная степень

    public Professor() {}

    public Professor(String name, String surname) {
        super (name, surname);
    }

    public Professor(String name, String surname, String sex) {
        super (name, surname, sex);
    }

    public Professor(String name, String surname, String sex, int age) {
        super (name, surname, sex, age);
    }

    public Professor(String name, String surname, String sex, int age, String speciality, String degree) {
        super (name, surname, sex, age);
        this.speciality = speciality;
        this.degree = degree;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public void printFullInfo() {
        super.printFullInfo();
        System.out.println("Speciality: " + speciality);
        System.out.println("Gegree: " + degree);
    }

    public void doRollCall(int groupIndex, ArrayList<Group> groups) {
        System.out.println("");

        boolean wholeGroupShirk = true;

        for (int i = 0; i < groups.get(groupIndex).groupSize(); i++) {
            if (groups.get(groupIndex).getStudentByIndex(i).getPresentToday()) {
                wholeGroupShirk = false;
                break;
            }
        }

        if (wholeGroupShirk) {
            System.out.println("");
            System.out.println("Holy cow!!! The whole group is shirking today! Will report to their dean now!");
            return;
        }

        System.out.println("");
        System.out.println("Hi Everybody!");
        Main.pressEnterToContinue();
        System.out.println("");
        System.out.println("Well, who is absent today?");
        Main.pressEnterToContinue();
        System.out.println("");

        for (int i = 0; i < groups.get(groupIndex).groupSize(); i++) {
            System.out.println("- " + groups.get(groupIndex).getStudentByIndex(i).getSurname() + "!");
            System.out.println("");
            Main.pressEnterToContinue();
            System.out.println("");

            if (groups.get(groupIndex).getStudentByIndex(i).getPresentToday()) {
                System.out.println("- Here!");
                System.out.println("");
                Main.pressEnterToContinue();
            } else {
                System.out.println("- Absent!");
                System.out.println("");
                Main.pressEnterToContinue();
            }
        }

        System.out.println("");
        System.out.println("Okay, so these students are absent today: ");

        for (int i = 0; i < groups.get(groupIndex).groupSize(); i++) {
            if (!groups.get(groupIndex).getStudentByIndex(i).getPresentToday()) {
                System.out.print(groups.get(groupIndex).getStudentByIndex(i).getSurname());
            }
        }

        System.out.println("");
        System.out.println("");
        System.out.println("No problem, I'll write their names down and we'll speak on the exam! :) ");
        System.out.println("All right, let's begin our class.");
        System.out.println("Where did we leave off last time?");
        System.out.println("...");
    }
}