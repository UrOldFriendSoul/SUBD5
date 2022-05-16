package logicLab5;


import modelsLab5.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class AppointmentLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create appointment");
        System.out.println("Insert 2 to read appointment");
        System.out.println("Insert 3 to update appointment");
        System.out.println("Insert 4 to delete appointment");
        System.out.println("Insert 5 to filter");

        Scanner sc = new Scanner(System.in);
        int i = 1;
        if(sc.hasNextInt())
            i = sc.nextInt();
        sc.close();
        Session session = null;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        switch (i) {
            case 1:
                create(session);
                break;
            case 2:
                read(session);
                break;
            case 3:
                update(session);
                break;
            case 4:
                delete(session);
                break;
            case 5:
                filterRead(session);
                break;
        }
        session.getTransaction().commit();
    }
    private void create(Session session) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert worker id");
        int worker_id = 1;
        if(sc.hasNextInt())
            worker_id = sc.nextInt();
        System.out.println("Insert shift id");
        int shift_id = 1;
        if(sc.hasNextInt())
            shift_id = sc.nextInt();
        System.out.println("Insert product id");
        int product_id = 1;
        if(sc.hasNextInt())
            product_id = sc.nextInt();
        Appointment appointment = new Appointment(worker_id, shift_id,product_id);
        session.save(appointment);
    }
    private void read(Session session) {
        List<Appointment> appointments = session.createQuery("SELECT a from Appointment a", Appointment.class).getResultList();
        System.out.println(appointments);
    }
    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert appointment id");
        int id = scanner.nextInt();

        System.out.println("Insert 1 to change worker id");
        System.out.println("Insert 2 to change shift id");
        System.out.println("Insert 3 to change product id");
        System.out.println("Insert 4 to do all");

        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Insert worker id");
                int worker_id = scanner.nextInt();
                Appointment appointment = session.get(Appointment.class, id);
                appointment.setId_worker(worker_id);
                session.save(session);
                break;
            case 2:
                System.out.println("Insert shift id");
                int shift_id = scanner.nextInt();
                Appointment appointment1 = session.get(Appointment.class, id);
                appointment1.setId_shift(shift_id);
                session.save(session);
                break;
            case 3:
                System.out.println("Insert product id");
                int product_id = scanner.nextInt();
                Appointment appointment2 = session.get(Appointment.class, id);
                appointment2.setId_product(product_id);
                session.save(session);
                break;
            case 4:
                System.out.println("Insert worker id");
                int worker_id2 = scanner.nextInt();
                System.out.println("Insert shift id");
                int shift_id2 = scanner.nextInt();
                System.out.println("Insert product id");
                int product_id2 = scanner.nextInt();
                Appointment appointment3 = session.get(Appointment.class, id);
                appointment3.setId_worker(worker_id2);
                appointment3.setId_shift(shift_id2);
                appointment3.setId_product(product_id2);
                break;
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert appointment id");
        int id = scanner.nextInt();
        Appointment appointment = session.get(Appointment.class, id);
        session.delete(appointment);
    }
    private void filterRead(Session session) {
        System.out.println("Insert price to filter by price");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Appointment> appointments = null;
        System.out.println("Insert price");
        int price = scanner.nextInt();
        //appointments = session.createQuery("SELECT a from Appointment a where price <= " + price, Appointment.class).getResultList();
    }
}
