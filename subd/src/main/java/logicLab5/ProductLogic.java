package logicLab5;


import modelsLab5.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class ProductLogic {
    public void work(SessionFactory sessionFactory) {
        System.out.println("Insert 1 to create product");
        System.out.println("Insert 2 to read product");
        System.out.println("Insert 3 to update product");
        System.out.println("Insert 4 to delete product");
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
        System.out.println("Insert product name");
        String name = scanner.next();
        System.out.println("Insert product price");
        int price = scanner.nextInt();
        Product product = new Product(name, price);
        session.save(product);
    }
    private void read(Session session) {
        List<Product> products = session.createQuery("SELECT a from Product a", Product.class).getResultList();
        System.out.println(products);
    }
    private void update(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product id");
        int id = scanner.nextInt();

        System.out.println("Insert 1 to change product name");
        System.out.println("Insert 2 to change product price");
        System.out.println("Insert 3 to do all");

        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Insert product name");
                String name = scanner.next();
                Product product = session.get(Product.class, id);
                product.setProduct_name(name);
                session.save(session);
                break;
            case 2:
                System.out.println("Insert product price");
                int price = scanner.nextInt();
                Product product1 = session.get(Product.class, id);
                product1.setProduct_price(price);
                session.save(product1);
                break;
            case 3:
                System.out.println("Insert product name");
                String name2 = scanner.next();
                System.out.println("Insert product price");
                int price2 = scanner.nextInt();
                Product product2 = session.get(Product.class, id);
                product2.setProduct_name(name2);
                product2.setProduct_price(price2);
                session.save(product2);
                break;
        }
    }
    private void delete(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert product id");
        int id = scanner.nextInt();
        Product product = session.get(Product.class, id);
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
        catch (Exception ex){
            System.out.println("cannot be deleted because it is in another entity");
        }
    }
    private void filterRead(Session session) {
        System.out.println("Insert price to filter by price");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Appointment> appointments = null;
        System.out.println("Insert price");
        int price = scanner.nextInt();
        appointments = session.createQuery("SELECT a from Appointment a where price <= " + price, Appointment.class).getResultList();
    }
}
