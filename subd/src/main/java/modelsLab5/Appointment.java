package modelsLab5;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "appointment", schema = "public", catalog = "test")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_appointment")
    private int id;
    @Column(name = "id_product")
    private int id_product;
    @Column(name = "id_worker")
    private int id_worker;
    @Column(name = "id_shift")
    private int id_shift;

    public Appointment(int id_product, int id_shift, int id_worker) {
        this.id_product = id_product;
        this.id_shift = id_shift;
        this.id_worker = id_worker;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || product id: %d || worker id: %d || shift id: %d", id, id_product, id_worker, id_shift);
    }
}