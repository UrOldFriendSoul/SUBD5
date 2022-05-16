package logicLab5;


import modelsLab5.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class ShiftLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create shift");
        System.out.println("Insert 2 to read shift");
        System.out.println("Insert 3 to update shift");
        System.out.println("Insert 4 to delete shift");
        System.out.println("Insert 5 to filter");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert shift date");
        String date = scanner.next();
        System.out.println("Insert shift week day");
        String shift_weekDay = scanner.next();
        Shift shift = new Shift(date, shift_weekDay);
        session.save(shift);
    }
    private void read(Session session) {
        List<Shift> shifts = session.createQuery("SELECT a from Shift a", Shift.class).getResultList();
        System.out.println(shifts);
    }
    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert shift id");
        int id = scanner.nextInt();

        System.out.println("Insert 1 to change shift date");
        System.out.println("Insert 2 to change shift week day");
        System.out.println("Insert 3 to do all");

        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Insert shift date");
                String date = scanner.next();
                Shift shift = session.get(Shift.class, id);
                shift.setShift_date(date);
                session.save(session);
                break;
            case 2:
                System.out.println("Insert shift week day");
                String day = scanner.next();
                Shift shift1 = session.get(Shift.class, id);
                shift1.setWeek_day(day);
                session.save(shift1);
                break;
            case 3:
                System.out.println("Insert shift date");
                String date2 = scanner.next();
                System.out.println("Insert shift week day");
                String day2 = scanner.next();
                Shift shift2 = session.get(Shift.class, id);
                shift2.setShift_date(date2);
                shift2.setWeek_day(day2);
                session.save(shift2);
                break;
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert shift id");
        int id = scanner.nextInt();
        Shift shift = session.get(Shift.class, id);
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(shift);
            transaction.commit();
        }
        catch (Exception ex){
            System.out.println("cannot be deleted because it is in another entity");
        }
    }
    private void filterRead(Session session) {
        System.out.println("Insert week day to filter by week day");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Appointment> appointments = null;
        System.out.println("Insert week day");
        String day = scanner.next();
        appointments = session.createQuery("SELECT a from Appointment a where week day = " + day, Appointment.class).getResultList();
    }
}
