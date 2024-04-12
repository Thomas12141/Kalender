import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter hourTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static Scanner scanner = new Scanner(System.in);
    private static final String falseInput = "False input.";
    private static final Kalenders kalenders = new Kalenders(10);

    private static String getName(){
        String name = scanner.next();
        if (name==null||name.isEmpty()){
            System.out.println("Name cant be null or empty!");
            return falseInput;
        }
        return name;
    }
    private static int createKalender(){
        System.out.print("Enter the name of the new calender:");
        String name = getName();
        if(falseInput.equals(name)){
            return 3;
        }

        return kalenders.addKalender(new Kalender(name));
    }

    private static int chooseKalender(){
        System.out.println(kalenders);
        System.out.println("Choose a calender number:");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        int choice;
        boolean success;
        while(true){
            try {
                System.out.println("Please give the number of the option that you want to choose:");
                if(kalenders.getElementsNumber()==0){
                    System.out.println("\t1. Create a new kalender.");
                    System.out.println("\t2. Exit the program.");
                    choice = scanner.nextInt();
                    if(choice==1){
                        createKalender();
                    }else if(choice==2){
                        System.exit(0);
                    }else if(choice==3){
                        System.out.println("You must choose a number between 1 and 2!");
                    }
                    continue;
                }

                System.out.println("\t1. Create a new calender.");
                System.out.println("\t2. Remove a calender.");
                System.out.println("\t3. Rename a calender.");
                System.out.println("\t4. Add a meeting to a calender.");
                System.out.println("\t5. Add a series of meeting to a calender.");
                System.out.println("\t6. Get free meetings.");
                System.out.println("\t7. Exit the program.");

                choice =  scanner.nextInt();
                if(choice == 1){
                    int result = createKalender();
                    if(result==1){
                        System.out.println("This calender name is already taken.");
                    }else if(result==2){
                        System.out.println("You cannot create more than 10 calenders.");
                    }
                }else if(choice == 2){
                    choice = chooseKalender();
                    if(choice>kalenders.getElementsNumber()){
                        System.out.println("There is not such calender number.");
                        continue;
                    }
                    kalenders.removeKalender(kalenders.getKalenders()[choice-1].getName());
                }else if(choice == 3){
                    choice = chooseKalender();
                    if(choice>kalenders.getElementsNumber()){
                        System.out.println("There is not such calender number.");
                        continue;
                    }
                    System.out.println("Give the new name of the calender:");
                    String name = getName();
                    if(falseInput.equals(name)){
                        continue;
                    }
                    success = kalenders.renameKalender(name, choice-1);
                    if(!success){
                        System.out.println("This name is already taken.");
                    }
                }
                else if(choice == 4){
                    choice = chooseKalender();
                    System.out.println("Give the name of the meeting:");
                    String name = scanner.next();
                    System.out.println("Give the date of the meeting, in the next format dd.MM.Year:");
                    String datum = scanner.next();
                    LocalDate localDate = LocalDate.parse(datum, dateTimeFormatter);
                    System.out.println("Give the start time of the meeting, in the next format HH:mm:");
                    String startTime = scanner.next();
                    LocalTime localStartTime = LocalTime.parse(startTime, hourTimeFormatter);
                    LocalDateTime start = LocalDateTime.of(localDate, localStartTime);
                    System.out.println("Give the end time of the meeting, in the next format HH:mm:");
                    String endTime = scanner.next();
                    LocalTime localEndTime = LocalTime.parse(endTime, hourTimeFormatter);
                    LocalDateTime end = LocalDateTime.of(localDate, localEndTime);
                    Termin termin = new Termin(name, start, end);
                    kalenders.getKalenders()[choice-1].addTermin(termin);
                }
                else if(choice == 5){
                    choice = chooseKalender();
                    System.out.println("Give the name of the meeting series:");
                    String name = scanner.next();
                    System.out.println("Give the date of the meeting, in the next format dd.MM.Year:");
                    String datum = scanner.next();
                    LocalDate localDate = LocalDate.parse(datum, dateTimeFormatter);
                    System.out.println("Give the start time of the meeting, in the next format HH:mm:");
                    String startTime = scanner.next();
                    LocalTime localStartTime = LocalTime.parse(startTime, hourTimeFormatter);
                    LocalDateTime start = LocalDateTime.of(localDate, localStartTime);
                    System.out.println("Give the end time of the meeting, in the next format HH:mm:");
                    String endTime = scanner.next();
                    LocalTime localEndTime = LocalTime.parse(endTime, hourTimeFormatter);
                    LocalDateTime end = LocalDateTime.of(localDate, localEndTime);
                    System.out.println("Give the number of the meetings:");
                    int number = scanner.nextInt();
                    System.out.println("Give the number of days between every meeting:");
                    int interval = scanner.nextInt();
                    Terminserie terminSerie = new Terminserie(name, number, start, end, interval);
                    kalenders.getKalenders()[choice-1].addSerie(terminSerie);
                }
                else if(choice == 6){
                    int firstCalender = chooseKalender();
                    int secondCalender = chooseKalender();
                    System.out.println("Give the start date of the optional meetings, in the next format dd.MM.Year:");
                    String date = scanner.next();
                    LocalDate startDate = LocalDate.parse(date, dateTimeFormatter);
                    System.out.println("Give the end date of the optional meetings, in the next format dd.MM.Year:");
                    date = scanner.next();
                    LocalDate endDate = LocalDate.parse(date, dateTimeFormatter);
                    System.out.println("Give the start time of the meeting, in the next format HH:mm:");
                    String time = scanner.next();
                    LocalTime startTime = LocalTime.parse(time, hourTimeFormatter);
                    System.out.println("Give the end time of the meeting, in the next format HH:mm:");
                    time = scanner.next();
                    LocalTime endTime = LocalTime.parse(time, hourTimeFormatter);
                    System.out.println("Give the length of the meeting in minutes:");
                    int length = scanner.nextInt();
                    System.out.println("Give the name of the meeting:");
                    String name = scanner.next();
                    Termin[] meetings = kalenders.getKalenders()[firstCalender-1].freieTermineFinden(kalenders.getKalenders()[secondCalender], startDate, endDate,
                            startTime, endTime, length, name);
                    for (Termin meeting : meetings) {
                        System.out.println(meeting);
                    }
                }
                else if(choice == 7){
                    scanner.close();
                    System.exit(0);
                }
            }catch (InputMismatchException e){
                System.out.println("You can give only an integer.");
                scanner = new Scanner(System.in);
            }
        }
    }

}
