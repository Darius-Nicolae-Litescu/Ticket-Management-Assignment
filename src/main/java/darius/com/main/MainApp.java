package darius.com.main;

import darius.com.dao.initializedata.DataInitializer;
import darius.com.service.TicketManagement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        new DataInitializer();
        Scanner scanner = new Scanner(System.in);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-configuration.xml");

        TicketManagement ticketManagement = applicationContext.getBean("ticketManagement", TicketManagement.class);

        printMenuInfo();
        int action = 10;
        while(action != 9){
            action = scanner.nextInt();
            switch(action){
                case(1):
                    System.out.println("Enter your username: ");
                    String username = scanner.next();
                    System.out.println("Enter your password: ");
                    String password = scanner.next();
                    ticketManagement.login(username, password);
                    break;
                case(2):
                    ticketManagement.showTrains();
                    break;
                case(3):
                    System.out.println("Enter the train number");
                    Long trainNumber = scanner.nextLong();
                    ticketManagement.bookTicket(trainNumber);
                    break;
                case(4):
                    System.out.println("Enter the ticket number");
                    Long ticketNumber = scanner.nextLong();
                    ticketManagement.cancelTicket(ticketNumber);
                    break;
                default:
                    System.out.println("Invalid action");
                    action = 9;
                    break;
            }
            System.out.println("Enter new command.");
            printMenuInfo();
        }
        System.out.println("Goodbye");
        System.exit(0);
    }
    private static void printMenuInfo(){
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to show trains");
        System.out.println("Press 3 to book ticket");
        System.out.println("Press 4 to cancel ticket");
        System.out.println("Press 9 to exit");
        System.out.println("\n\n");
    }
}
