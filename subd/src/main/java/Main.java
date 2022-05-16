import logicLab5.*;
import modelsLab5.Appointment;
import modelsLab5.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import modelsLab5.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Appointment.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Shift.class)
                .addAnnotatedClass(Worker.class)
                .buildSessionFactory();

        boolean isWork = true;
        while(isWork){
            System.out.println("Insert 1 to work with appointments");
            System.out.println("Insert 2 to work with products");
            System.out.println("Insert 3 to work with shifts");
            System.out.println("Insert 4 to work with workers");
            System.out.println("Insert 6 to exit");
            System.out.println("Insert 7 to see the main request");

            Scanner sc = new Scanner(System.in);
            int i = 4;
            if(sc.hasNextInt())
                i = sc.nextInt();
                switch (i){
                case 1:
                    AppointmentLogic appointmentLogic = new AppointmentLogic();
                    appointmentLogic.work(sessionFactory);
                    break;
                case 2:
                    ProductLogic productLogic = new ProductLogic();
                    productLogic.work(sessionFactory);
                    break;
                case 3:
                    ShiftLogic shiftLogic = new ShiftLogic();
                    shiftLogic.work(sessionFactory);
                    break;
                case 4:
                    WorkerLogic workerLogic = new WorkerLogic();
                    workerLogic.work(sessionFactory);
                    break;
                case 6:
                    isWork = false;
                    break;
                case 7:
                    MainSQLRequestLogic mainSQLRequestLogic = new MainSQLRequestLogic();
                    mainSQLRequestLogic.work(sessionFactory);
                    break;
            }
        }
        sessionFactory.close();
    }
}