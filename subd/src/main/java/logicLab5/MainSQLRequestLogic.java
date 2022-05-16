package logicLab5;

import modelsLab5.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class MainSQLRequestLogic {
    public void work (SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Appointment> appointments = session.createQuery("SELECT a FROM Appointments a",
                Appointment.class).getResultList();
        System.out.println(" Worker_id\t\t\t\tShift_id\t\t\tProduct_id");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.format("\n%s\t\t\t%s\t\t\t%s", appointments.get(i).getId_worker(),
                    appointments.get(i).getId_shift(), appointments.get(i).getId_product());
        }
        session.getTransaction().commit();
    }
}
