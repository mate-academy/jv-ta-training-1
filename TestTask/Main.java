import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        ArrayList<Professor> professors = new ArrayList<Professor>(10);
        ArrayList<Group> groups = new ArrayList<Group>(5);

        while (true) {
            printMenu();

            switch (getInput()) {
                case 1:
                    addGroup(groups);
                    pressEnterToContinue();
                    break;
                case 2:
                    addStudentToGroup(groups);
                    pressEnterToContinue();
                    break;
                case 3:
                    addProfessor(professors);
                    pressEnterToContinue();
                    break;
                case 4:
                    showAllGroups(groups);
                    pressEnterToContinue();
                    break;
                case 5:
                    showStudentsInGroup(groups);
                    pressEnterToContinue();
                    break;
                case 6:
                    showAllProfessors(professors);
                    pressEnterToContinue();
                    break;
                case 7:
                    showAllStudents(groups);
                    pressEnterToContinue();
                    break;
                case 8:
                    deleteGroup(groups);
                    pressEnterToContinue();
                    break;
                case 9:
                    deleteStudent(groups);
                    pressEnterToContinue();
                    break;
                case 10:
                    deleteProfessor(professors);
                    pressEnterToContinue();
                    break;
                case 11:
                    moveStudent(groups);
                    pressEnterToContinue();
                    break;
                case 12:
                    rollCall(groups, professors);
                    pressEnterToContinue();
                    break;
                case 13:
                    electMonitor (groups);
                    pressEnterToContinue();
                    break;
                case 14:
                    System.out.println("");
                    System.out.println("This function is currently under construction!");
                    System.out.println("Check for future releases.");
                    pressEnterToContinue();
                    break;
                case 15:
                    loadDataBase(groups, professors);
                    pressEnterToContinue();
                    break;
                case 16:
                    System.exit(0);
                    break;
            }
        }
    }


    // Вывод меню
    public static void printMenu() {
        System.out.println("                                                             ");
        System.out.println("     +----------------------------------------------------+  ");
        System.out.println("     |                FreshMen v1.0 Beta                  |  ");
        System.out.println("     |             ------------------------               |  ");
        System.out.println("     |                   MAIN MENU                        |  ");
        System.out.println("     +----------------------------------------------------+  ");
        System.out.println("                                                             ");
        System.out.println("              1. Add a group.                                ");
        System.out.println("              2. Add a student into the group.               ");
        System.out.println("              3. Add a professor.                            ");
        System.out.println("              4. Show all groups list.                       ");
        System.out.println("              5. Show all students in a group.               ");
        System.out.println("              6. Show all professors list.                   ");
        System.out.println("              7. Show full students list.                    ");
        System.out.println("              8. Delete a group.                             ");
        System.out.println("              9. Delete a student from the group.            ");
        System.out.println("             10. Delete a professor.                         ");
        System.out.println("             11. Move a student to another group.            ");
        System.out.println("             12. Do the roll-call of the students in a group.");
        System.out.println("             13. Elect the Monitor of a group.               ");
        System.out.println("             14. Save the database.                          ");
        System.out.println("             15. Load the database.                          ");
        System.out.println("             16. Exit.                                       ");
        System.out.println("                                                             ");
    }


    // Считывам выбор пункта меню
    public static int getInput() {

        final int MENU_ITEM_NUMBER = 16;
        int input = 0;


        System.out.print("Please choose a menu item from the 1st to the " + MENU_ITEM_NUMBER + "th inclusive: ");

        do {
            input = getValidInt();
            if (input > 0 && input <= MENU_ITEM_NUMBER) {
                break;
            } else if (input == 0) {
            } else {
                System.out.print("Incorrect range, please try again: ");
            }

        }
        while (true);

        return input;
    }


    // (1) Добавляем группу в базу
    public static void addGroup(ArrayList<Group> groups) {

        String title;
        int tmp;

        System.out.print("Enter group title: ");
        title = getString();

        tmp = groups.size();
        // Создаём новый объект и добавляем его в ArrayList
        groups.add(new Group(title));
        System.out.println("");

        // Проверка добавления группы
        if (groups.size() - tmp == 1) {
            System.out.println("The group was added successfully!");
        } else {
            System.out.println("An unknown error occurred while trying to add the group!");
        }
    }


    // (2) Добавляем студента в одну из имеющихся групп
    public static void addStudentToGroup(ArrayList<Group> groups) {

        if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database, impossible to add a student!");
            return;
        }

        int groupIndex = 0;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the group, in which you want to add a student: ");

        while (true) {
            if (groupIndex > 0 && groupIndex <= groups.size()) {
                break;
            } else {
                groupIndex = getValidInt();
            }
        }

        groupIndex--;

        groups.get(groupIndex).addStudent();
    }


    // (3) Добавляем профессора в базу
    public static void addProfessor(ArrayList<Professor> professors) {

        String name, surname, sex, speciality, degree;
        int age, tmp;

        System.out.print("Enter name of the professor: ");
        name = getString();
        System.out.print("Enter surname of the professor: ");
        surname = getString();
        System.out.print("Enter sex of the professor (male|female): ");
        sex = getSex();
        System.out.print("Enter age of the professor (30-80): ");
        age = getValidAge(false);
        System.out.print("Enter speciality of the professor: ");
        speciality = getString();
        System.out.print("Enter degree of the professor: ");
        degree = getString();

        tmp = professors.size();
        // Создаём новый объект и добавляем его в ArrayList
        professors.add(new Professor(name, surname, sex, age, speciality, degree));
        System.out.println("");

        // Проверка добавления профессора
        if (professors.size() - tmp == 1) {
            System.out.println("The professor was added successfully!");
        } else {
            System.out.println("An unknown error occurred while trying to add the professor!");
        }
    }


    // (4) Выводим на экран список групп
    public static void showAllGroups(ArrayList<Group> groups) {

        if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database!");
            return;
        }

        System.out.println("");
        System.out.println("Full group list: ");
        System.out.println("---------------- ");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println(i+1 + ". " + groups.get(i).getTitle());
        }
        System.out.println("");
    }


    // (5) Отобразить список студентов в выбранной группе
    public static void showStudentsInGroup(ArrayList<Group> groups) {

        if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database, nothing to show!");
            return;
        }

        int groupIndex = 0;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the group to show all its students: ");

        while (true) {
            if (groupIndex > 0 && groupIndex <= groups.size()) {
                break;
            } else {
                groupIndex = getValidInt();
            }
        }

        groupIndex--;

        groups.get(groupIndex).printAllStudents();
    }


    // (6) Выводим на экран список профессоров
    public static void showAllProfessors(ArrayList<Professor> professors) {
        if (professors.isEmpty()) {
            System.out.println("");
            System.out.println("There are no professors in the database!");
            return;
        }

        System.out.println("");
        System.out.println("Full professor list: ");
        System.out.println("-------------------- ");
        for (int i = 0; i < professors.size(); i++) {
            System.out.println("----------------------------------------------------");
            professors.get(i).printFullInfo();
            System.out.println("----------------------------------------------------");
            System.out.println("");
        }
        System.out.println("");
    }


    // (7) Отобразить список всех студентов в базе по группам
    public static void showAllStudents(ArrayList<Group> groups) {
        for (Group object: groups) {
            object.printAllStudents();
        }
    }


    // (8) Удалить группу
    public static void deleteGroup(ArrayList<Group> groups) {
        if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database, nothing to delete!");
            return;
        }

        System.out.println("");
        System.out.print("Warning! You are going to delete a group from the database. Continue? (y/n): ");

        if (!checkY()) {
            return;
        }

        int groupIndex = 0;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the group, which you want to delete: ");

        while (true) {
            if (groupIndex > 0 && groupIndex <= groups.size()) {
                break;
            } else {
                groupIndex = getValidInt();
            }
        }

        groupIndex--;
        System.out.println("");
        System.out.println("");
        System.out.println("!!! Last chance !!!");
        System.out.print("Are you sure you want to delete the group '" +
                        groups.get(groupIndex).getTitle() + "' with all its students permanently? (y/n): ");

        if (!checkY()) {
            return;
        }

        int tmp = groups.size();
        groups.remove(groupIndex);

        // Проверка удаления группы
        if (tmp - groups.size() == 1) {
            System.out.println("");
            System.out.println("The group has been deleted successfully!");
        } else {
            System.out.println("An unknown error occurred while trying to delete the group!");
        }
    }


    // (9) Удалить студента из группы
    public static void deleteStudent(ArrayList<Group> groups) {
        if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database, there is no one to delete!");
            return;
        }

        System.out.println("");
        System.out.print("Warning! You are going to delete a student from a group. Continue? (y/n): ");

        if (!checkY()) {
            return;
        }

        int groupIndex = 0, studentIndex = 0;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the group, from which you want to delete a student: ");

        while (true) {
            if (groupIndex > 0 && groupIndex <= groups.size()) {
                break;
            } else {
                groupIndex = getValidInt();
            }
        }

        groupIndex--;

        System.out.println("");
        System.out.println("Full list of students in the group '" + groups.get(groupIndex).getTitle() + "': ");
        groups.get(groupIndex).printShortStudentList();
        System.out.println("");
        System.out.print("Enter the number of the student, which you want to delete from the group '" +
                groups.get(groupIndex).getTitle() + "': ");

        while (true) {
            if (studentIndex > 0 && studentIndex <= groups.get(groupIndex).groupSize()) {
                break;
            } else {
                studentIndex = getValidInt();
            }
        }

        studentIndex--;

        System.out.println("");
        System.out.println("");
        System.out.println("!!! Last chance !!!");
        System.out.print("Are you sure you want to delete the student '" +
                        groups.get(groupIndex).getNameAndSurname(studentIndex) + "' from the group '" +
                groups.get(groupIndex).getTitle() + "' permanently? (y/n): ");

        if (!checkY()) {
            return;
        }

        int tmp = groups.get(groupIndex).groupSize();
        groups.get(groupIndex).deleteStudent(studentIndex);

        // Проверка удаления студента из группы
        if (tmp - groups.get(groupIndex).groupSize() == 1) {
            System.out.println("");
            System.out.println("The student has been deleted successfully!");
        } else {
            System.out.println("An unknown error occurred while trying to delete the student!");
        }
    }


    // (10) Удалить профессора из базы
    public static void deleteProfessor(ArrayList<Professor> professors) {
        if (professors.isEmpty()) {
            System.out.println("");
            System.out.println("There are no professors in the database, there is no one to delete!");
            return;
        }

        System.out.println("");
        System.out.print("Warning! You are going to delete a professor from the database. Continue? (y/n): ");

        if (!checkY()) {
            return;
        }

        int professorIndex = 0;

        System.out.println("");
        System.out.print("Full professors list: ");

        for (int i = 0; i < professors.size(); i++) {
            System.out.print("(" + (i+1) + ")'" + professors.get(i).getName() + " " +
                    professors.get(i).getSurname() + "'  ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the professor, which you want to delete: ");

        while (true) {
            if (professorIndex > 0 && professorIndex <= professors.size()) {
                break;
            } else {
                professorIndex = getValidInt();
            }
        }

        professorIndex--;
        System.out.println("");
        System.out.println("");
        System.out.println("!!! Last chance !!!");
        System.out.print("Are you sure you want to delete the professor '" + professors.get(professorIndex).getName() +
                " " + professors.get(professorIndex).getSurname() + "' permanently? (y/n): ");

        if (!checkY()) {
            return;
        }

        int tmp = professors.size();
        professors.remove(professorIndex);

        // Проверка удаления профессора
        if (tmp - professors.size() == 1) {
            System.out.println("");
            System.out.println("The professor has been deleted successfully!");
        } else {
            System.out.println("An unknown error occurred while trying to delete the professor!");
        }
    }


    // (11) Переводим студента в другую группу
    public static void moveStudent(ArrayList<Group> groups) {

        if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database, there is no one to move!");
            return;
        } else if (groups.size() == 1){
            System.out.println("");
            System.out.println("There is one group only in the database, nowhere to move!");
            return;
        }

        int sourceGroupIndex = 0, targetGroupIndex = 0, studentIndex = 0;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the source group: ");

        while (true) {
            if (sourceGroupIndex > 0 && sourceGroupIndex <= groups.size()) {
                break;
            } else {
                sourceGroupIndex = getValidInt();
            }
        }

        sourceGroupIndex--;

        if (groups.get(sourceGroupIndex).groupSize() == 0) {
            System.out.println("");
            System.out.println("There are no students in this groups, there is no one to move!");
            return;
        }

        System.out.println("");
        System.out.println("Full students list of '" + groups.get(sourceGroupIndex).getTitle() + "' group: " );
        groups.get(sourceGroupIndex).printShortStudentList();

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the student to move: ");

        while (true) {
            if (studentIndex > 0 && studentIndex <= groups.get(sourceGroupIndex).groupSize()) {
                break;
            } else {
                studentIndex = getValidInt();
            }
        }

        studentIndex--;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the target group: ");

        while (true) {
            if (targetGroupIndex > 0 && targetGroupIndex <= groups.size()) {
                break;
            } else {
                targetGroupIndex = getValidInt();
            }
        }

        targetGroupIndex--;

        if (targetGroupIndex == sourceGroupIndex) {
            System.out.println("The student is in this group already! It's impossible to move.");
            return;
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Are you sure you want to move the student '" +
                groups.get(sourceGroupIndex).getNameAndSurname(studentIndex) + "' from the group '" +
                groups.get(sourceGroupIndex).getTitle() + "' to the group '" +
                groups.get(targetGroupIndex).getTitle() + "'? (y/n): ");

        if (!checkY()) {
            return;
        }

        int tmp1 = groups.get(sourceGroupIndex).groupSize();
        int tmp2 = groups.get(targetGroupIndex).groupSize();

        // Переводим студента в другую группу
        groups.get(targetGroupIndex).moveStudent( groups.get(sourceGroupIndex).getStudentByIndex(studentIndex) );
        groups.get(sourceGroupIndex).deleteStudent(studentIndex);

        // Проверка перевода студента в другую группу
        if (tmp1 - groups.get(sourceGroupIndex).groupSize() == 1 &&
                groups.get(targetGroupIndex).groupSize() - tmp2 == 1) {
            System.out.println("");
            System.out.println("The student has been moved successfully!");
        } else {
            System.out.println("An unknown error occurred while trying to move the student!");
        }
    }


    // (12) Сделать перекличку в группе
    public static void rollCall(ArrayList<Group> groups, ArrayList<Professor> professors) {
        if (groups.isEmpty() && professors.isEmpty()) {
            System.out.println("");
            System.out.println("There are nor groups, nor professors in the database, impossible to do a roll-call!");
            return;
        } else if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database, nowhere to do a roll-call!");
            return;
        } else if (professors.isEmpty()) {
            System.out.println("");
            System.out.println("There are no professors in the database, there is nobody to do a roll-call!");
            return;
        }

        int professorIndex = 0, groupIndex = 0;

        System.out.println("");
        System.out.print("Full professors list: ");

        for (int i = 0; i < professors.size(); i++) {
            System.out.print("(" + (i+1) + ")'" + professors.get(i).getName() + " " +
                    professors.get(i).getSurname() + "'  ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the professor, which should do a roll-call: ");

        while (true) {
            if (professorIndex > 0 && professorIndex <= professors.size()) {
                break;
            } else {
                professorIndex = getValidInt();
            }
        }

        professorIndex--;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the group, in which the professor should do a roll-call: ");

        while (true) {
            if (groupIndex > 0 && groupIndex <= groups.size()) {
                break;
            } else {
                groupIndex = getValidInt();
            }
        }

        groupIndex--;

        if (groups.get(groupIndex).groupSize() == 0) {
            System.out.println("The group isn't full yet! It's impossible to do a roll-call now!");
            return;
        }

        groups.get(groupIndex).generatePresenceStatuses();
        professors.get(professorIndex).doRollCall(groupIndex, groups);
    }


    // (13) Выбрать старосту группы
    public static void electMonitor (ArrayList<Group> groups) {
        if (groups.isEmpty()) {
            System.out.println("");
            System.out.println("There are no groups in the database, nowhere to elect a Monitor!");
            return;
        }

        int groupIndex = 0;

        System.out.println("");
        System.out.print("Full group list: ");

        for (int i = 0; i < groups.size(); i++) {
            System.out.print("(" + (i+1) + ")" + groups.get(i).getTitle() + " ");
        }

        System.out.println("");
        System.out.println("");
        System.out.print("Enter the number of the group, in which it's necessary to elect a Monitor: ");

        while (true) {
            if (groupIndex > 0 && groupIndex <= groups.size()) {
                break;
            } else {
                groupIndex = getValidInt();
            }
        }

        groupIndex--;

        groups.get(groupIndex).doElectMonitor();
    }


    // Считывам корректный int с клавиатуры
    public static int getValidInt() {
        int input = 0;
        Scanner scanner = new Scanner(System.in); // Создаём объект класса Scanner

        // Ожидаем данные с потока ввода, возвращает true, если введённое значение можно интерпретировать как int
        if ( scanner.hasNextInt() ) {
            input = scanner.nextInt();   // Считываем проверенное значение из потока ввода и сохраняем
        } else {
            System.out.print("Wrong data, please enter an integer: ");
            scanner.nextLine();         // Считываем строку из буфера перед следующим вводом
        }

        return input;
    }


    // Считывам строку с клавиатуры
    public static String getString() {
        String input = "";
        Scanner scanner = new Scanner(System.in);

        do {
            if ( scanner.hasNextLine() ) {
                input = scanner.nextLine();
            } else {
                System.out.print("Wrong data, please enter a string: ");
                scanner.nextLine();
            }
        } while (input.equals(""));

        return input;
    }


    // Считывам корректный пол с клавиатуры
    public static String getSex() {
        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            input = scanner.nextLine();
            if (input.equals("male") || input.equals("female")) {
                break;
            } else {
                System.out.print("Wrong input, please enter \"male\" or \"female\": ");
            }
        }

        return input;
    }


    // Считывам корректный возраст с клавиатуры
    public static int getValidAge(boolean flag) {
        int input = getValidInt();

        while (true) {
            if (input >= 17 && input <= 60 && flag) {
                break;
            } else if (input >= 30 && input <= 80) {
                break;
            } else {
                input = getValidInt();
            }
        }

        return input;
    }


    public static void pressEnterToContinue() {
        System.out.println("");
        System.out.print("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean checkY() {
        while (true) {
            String respond = getString();
            if (respond.equals("n") || respond.equals("N")) {
                return false;
            } else if (respond.equals("y") || respond.equals("Y")) {
                return true;
            }
        }
    }


    // (15) Загружаем базу данных из файла
    public static void loadDataBase(ArrayList<Group> groups, ArrayList<Professor> professors) {
        System.out.println("");

        System.out.print("Warning! Current database state will be lost! Continue? (y/n): ");

        if (!checkY()) {
            return;
        }

        groups.clear();
        professors.clear();
        System.out.print("Enter full path to the file: ");
        String path = getString();

        try {
            FileInputStream fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            String[] words;
            int counterGroups = 0, counterProfessors = 0, counterStudents = 0;

            while ((strLine = br.readLine()) != null) {
                words = strLine.split("@@");

                if (!words[0].equals("/*!")) {
                    groups.clear();
                    professors.clear();
                    System.out.println("This file isn't save file or is corrupted!");
                    System.out.println("To avoid errors, current database has been cleared.");
                    return;
                }

                if (words[1].equals("0")) {
                    if (loadGroupLine(groups, words[2]) ) {
                        counterGroups++;
                    }
                } else if (words[1].equals("1")) {
                    if (loadProfessorLine(professors, words[3], words[2], words[4], words[5], words[6], words[7]) ) {
                        counterProfessors++;
                    }
                } else if (words[1].equals("2")) {
                    if ( groups.get(Integer.parseInt(words[2])).loadStudent(
                            words[4], words[3], words[5], Integer.parseInt(words[6]), words[2],
                            Double.parseDouble(words[7]) ) ) {
                        counterStudents++;
                    }
                } else {
                    System.out.println("This file isn't save file or is corrupted!");
                    System.out.println("To avoid errors, current database has been cleared.");
                    return;
                }
            }
            System.out.println("");
            System.out.println("Was successfully loaded: " + counterGroups + " groups, " + counterProfessors +
                    " professors, and " + counterStudents + " students!");

        } catch (IOException e){
            System.out.println("");
            System.out.println("File reading error!");
            pressEnterToContinue();
        }
    }


    public static boolean loadGroupLine(ArrayList<Group> groups, String title) {
        int tmp = groups.size();
        groups.add(new Group(title));
        return groups.size() - tmp == 1;
    }


    public static boolean loadProfessorLine(ArrayList<Professor> professors, String name, String surname, String sex,
                                            String age, String speciality, String degree) {
        int tmp = professors.size();
        professors.add(new Professor(name, surname, sex, Integer.parseInt(age), speciality, degree));
        return professors.size() - tmp == 1;
    }
}