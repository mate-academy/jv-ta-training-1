import java.util.ArrayList;
import java.util.Scanner;

public class Group {

    private String title;
    private ArrayList<Student> students;

    public Group(String title) {
        this.title = title;
        students = new ArrayList<Student>(20);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameAndSurname(int index) {
        return students.get(index).getName() + " " + students.get(index).getSurname();
    }


    public void printStudentList() {
        if (students.isEmpty()) {
            System.out.println("");
            System.out.println("There are no students in '" + this.getTitle() + "' group!");
            Main.pressEnterToContinue();
            return;
        }

        System.out.println("");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("----------------------------------------------------");
            students.get(i).printFullInfo();
            System.out.println("----------------------------------------------------");
            System.out.println("");
        }
        System.out.println("");

        Main.pressEnterToContinue();
    }


    public void printShortStudentList() {
        if (students.isEmpty()) {
            System.out.println("");
            System.out.println("There are no students in '" + this.getTitle() + "' group!");
            Main.pressEnterToContinue();
            return;
        }

        System.out.println("");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("(" + (i+1) + ")'" + students.get(i).getName() + " " + students.get(i).getSurname() + "'");
        }
        System.out.println("");
    }


    // Считывам корректный средний балл с клавиатуры
    public static double getValidGradeAverage() {
        double input = 0.0;
        Scanner scanner = new Scanner(System.in);

        do {
            if ( scanner.hasNextDouble() ) {
                input = scanner.nextDouble();
            } else {
                System.out.print("Wrong data, please enter a double: ");
                scanner.nextLine();
            }
        } while ( input < 1.0 || input > 100.0 );

        return input;
    }


    // Добавляем студента в группу
    public void addStudent() {
        String name, surname, sex, group;
        int age;
        double gradeAverage;
        int tmp;

        System.out.print("Enter name of the student: ");
        name = Main.getString();
        System.out.print("Enter surname of the student: ");
        surname = Main.getString();
        System.out.print("Enter sex of the student (male|female): ");
        sex = Main.getSex();
        System.out.print("Enter age of the student (17-60): ");
        age = Main.getValidAge(true);
        System.out.print("Enter grade point average of the student: ");
        gradeAverage = getValidGradeAverage();
        group = this.getTitle();

        tmp = students.size();
        // Создаём новый объект и добавляем его в ArrayList
        students.add(new Student(name, surname, sex, age, group, gradeAverage));
        System.out.println("");

        // Проверка добавления студента
        if (students.size() - tmp == 1) {
            System.out.println("The student was added successfully!");
        } else {
            System.out.println("An unknown error occurred while trying to add the student!");
        }
    }


    // Загружаем студента в группу
    public boolean loadStudent(String name, String surname, String sex, int age, String group, double gradeAverage) {
        int tmp = students.size();
        students.add(new Student(name, surname, sex, age, group, gradeAverage));
        return students.size() - tmp == 1;
    }


    // Удаляем студента из группы
    public void deleteStudent(int index) {
        students.remove(index);
    }


    // Получаем объект Student по индексу в ArrayList
    public Student getStudentByIndex(int index) {
        return students.get(index);
    }


    // Переводим студента в другую группу
    public void moveStudent(Student student) {
        students.add(student);
    }


    // Вывести список всех студентов в группе
    public void printAllStudents () {
        if (students.isEmpty()) {
            System.out.println("");
            System.out.println("There are no students in '" + this.getTitle() + "' group!");
            return;
        }

        System.out.println("");
        System.out.println("Full students list in group '" + this.getTitle() + "': ");
        System.out.println("---------------------------------- ");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("----------------------------------------------------");
            students.get(i).printFullInfo();
            System.out.println("----------------------------------------------------");
            System.out.println("");
        }
        System.out.println("");
    }


    // Выборы старосты
    public void doElectMonitor () {
        // Выбираем 3 главных кандидатов в старосты с максимальными значениями по личным качествам

        if (students.size() < 10) {
            System.out.println("The group isn't full yet, it's impossible to elect a Monitor right now!");
            return;
        }

        double[] results = new double[students.size()];
        int[] candidates = new int[3];
        int[] votes = {0, 0, 0};

        for (int i = 0; i < students.size(); i++) {
            if (!students.get(i).getWantsToBeLeader()) {
                results[i] = -1;
            } else {
                results[i] = students.get(i).getRespect() * 3 + students.get(i).getResponsibility() * 2 +
                        students.get(i).getSocialActivity() + students.get(i).getGradeAverage() *0.5;
            }
        }

        // Индексы 3 главных кандидатов в старосты
        for (int i = 0; i < 3; i++) {
            candidates[i] = getMaxArrayValueIndex(results);
            results[ candidates[i] ] = -1;
        }

        // Голосование остальных студентов группы за одного из 3 кандидатов, исходя из личных предпочтений
        for (int i = 0; i < students.size(); i++) {
            if (i == candidates[0] || i == candidates[1] || i == candidates[2]) {
                continue;
            }

            switch ((int) (Math.random() + 3)) {
                case 0:
                    votes[0]++;
                    break;
                case 1:
                    votes[1]++;
                    break;
                case 2:
                    votes[2]++;
                    break;
            }
        }

        System.out.println("");
        System.out.println("Hurrah! In the result of free and fair elections, the Monitor was elected!");
        System.out.println("This student is :");
        System.out.println("");
        students.get(candidates[getMaxArrayValueIndex(votes)]).printFullInfo();
    }


    // Найти индекс максимального элемента массива double
    public int getMaxArrayValueIndex(double[] arr){
        double max = arr[0];
        int maxIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    // Найти индекс максимального элемента массива int
    public int getMaxArrayValueIndex(int[] arr){
        int max = arr[0];
        int maxIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }


    // Размер группы
    public int groupSize () {
        return students.size();
    }


    // Сгенерировать присутствующих и отсутствующих
    public void generatePresenceStatuses () {
        for (Student stud : students) {
            stud.setPresentToday();
        }
    }
}