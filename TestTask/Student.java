public class Student extends Person {

    private String group; // Группа студента
    private double gradeAverage; // Средний балл (Используется в выборах старосты с коэффициентом: 0,5)

    private boolean presentToday; // Присутствует ли сегодня на паре

    private boolean wantsToBeLeader; // Желание быть старостой (Кандидатами в выборах старосты могут быть только желающие)
    private double respect; // Уважение к студенту в группе (Используется в выборах старосты с коэффициентом: 3,0)
    private double responsibility; // Уровень ответственности (Используется в выборах старосты с коэффициентом: 2,0)
    private double socialActivity; // Уровень социальной активности (Используется в выборах старосты с коэффициентом: 1,0)


    public String getGroup() {
        return group;
    }

    public double getGradeAverage() {
        return gradeAverage;
    }

    public void setGradeAverage(double gradeAverage) {
        this.gradeAverage = gradeAverage;
    }

    public boolean getPresentToday() {
        return presentToday;
    }

    public void setPresentToday() {
        this.presentToday = Math.random() > 0.5;
    }

    public boolean getWantsToBeLeader() {
        return wantsToBeLeader;
    }

    public double getRespect() {
        return respect;
    }

    public double getResponsibility() {
        return responsibility;
    }

    public double getSocialActivity() {
        return socialActivity;
    }

    public Student() {
        this.wantsToBeLeader = Math.random() > 0.5;
        this.respect = Math.random() * 100 + 1;
        this.responsibility = Math.random() * 100 + 1;
        this.socialActivity = Math.random() * 100 + 1;
    }

    public Student(String name, String surname) {
        super (name, surname);
        this.wantsToBeLeader = Math.random() > 0.5;
        this.respect = Math.random() * 100 + 1;
        this.responsibility = Math.random() * 100 + 1;
        this.socialActivity = Math.random() * 100 + 1;
    }

    public Student(String name, String surname, String sex) {
        super (name, surname, sex);
        this.wantsToBeLeader = Math.random() > 0.5;
        this.respect = Math.random() * 100 + 1;
        this.responsibility = Math.random() * 100 + 1;
        this.socialActivity = Math.random() * 100 + 1;
    }

    public Student(String name, String surname, String sex, int age) {
        super (name, surname, sex, age);
        this.wantsToBeLeader = Math.random() > 0.5;
        this.respect = Math.random() * 100 + 1;
        this.responsibility = Math.random() * 100 + 1;
        this.socialActivity = Math.random() * 100 + 1;
    }

    public Student(String name, String surname, String sex, int age, String group, double gradeAverage) {
        super (name, surname, sex, age);
        this.group = group;
        this.gradeAverage = gradeAverage;
        this.wantsToBeLeader = Math.random() > 0.5;
        this.respect = Math.random() * 100 + 1;
        this.responsibility = Math.random() * 100 + 1;
        this.socialActivity = Math.random() * 100 + 1;
    }

    @Override
    public void printFullInfo() {
        super.printFullInfo();
        System.out.println("Group: " + group);
        System.out.println("Grade Point Average: " + gradeAverage);
    }
}