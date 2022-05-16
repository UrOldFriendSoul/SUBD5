package logicLab5;

import modelsLab5.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class WorkerLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create worker");
        System.out.println("Insert 2 to read worker");
        System.out.println("Insert 3 to update worker");
        System.out.println("Insert 4 to delete worker");
        System.out.println("Insert 5 to filter");

        Scanner scanner = new Scanner(System.in);
        int i = 0;
        if(scanner.hasNextInt())
        i = scanner.nextInt();
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
        System.out.println("Insert worker name");
        String name = scanner.next();
        System.out.println("Insert worker job evaluation");
        int workerJob_Evaluation = scanner.nextInt();
        System.out.println("Insert worker first working date");
        String workerFirst_WorkingDate = scanner.next();
        Worker worker = new Worker(name, workerJob_Evaluation, workerFirst_WorkingDate);
        session.save(worker);
    }
    private void read(Session session) {
        List<Worker> workers = session.createQuery("SELECT a from Worker a", Worker.class).getResultList();
        System.out.println(workers);
    }
    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert worker id");
        int id = scanner.nextInt();

        System.out.println("Insert 1 to change name");
        System.out.println("Insert 2 to change worker job evaluation");
        System.out.println("Insert 3 to change worker first working date");
        System.out.println("Insert 4 to do all");

        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Insert worker name");
                String name = scanner.next();
                Worker worker = session.get(Worker.class, id);
                worker.setWorkerName(name);
                session.save(worker);
                break;
            case 2:
                System.out.println("Insert worker job evaluation");
                int number = scanner.nextInt();
                Worker worker1 = session.get(Worker.class, id);
                worker1.setWorkerJob_Evaluation(number);
                session.save(worker1);
                break;
            case 3:
                System.out.println("Insert worker first working date");
                String fdate = scanner.next();
                Worker worker2 = session.get(Worker.class, id);
                worker2.setWorkerFirst_WorkingDate(fdate);
                session.save(worker2);
            case 4:
                System.out.println("Insert worker name");
                String name2 = scanner.next();
                System.out.println("Insert worker job evaluation");
                int number2 = scanner.nextInt();
                System.out.println("Insert worker first working date");
                String fdate2 = scanner.next();
                Worker worker3 = session.get(Worker.class, id);
                worker3.setWorkerName(name2);
                worker3.setWorkerJob_Evaluation(number2);
                worker3.setWorkerFirst_WorkingDate(fdate2);
                session.save(worker3);
                break;
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert worker id");
        int id = scanner.nextInt();
        Worker worker = session.get(Worker.class, id);
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(worker);
            transaction.commit();
        }
        catch (Exception ex){
            System.out.println("cannot be deleted because it is in another entity");
        }
    }
    private void filterRead(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert worker name");
        String name = scanner.next();
        List<Worker> workers = session.createQuery("SELECT a from Worker a WHERE workerName = \'" + name + "\'", Worker.class).getResultList();
        System.out.println(workers);
    }
}
