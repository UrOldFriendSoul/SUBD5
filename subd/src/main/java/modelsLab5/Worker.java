package modelsLab5;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "worker", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Worker {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_worker")
    private int id;
    @Column(name = "name_worker")
    private String workerName;
    @Column(name = "workerJob_Evaluation")
    private int workerJob_Evaluation;
    @Column(name = "workerFirst_WorkingDate")
    private String workerFirst_WorkingDate;

    public Worker(String workerName, int workerJob_Evaluation,String workerFirst_WorkingDate) {
        this.workerName = workerName;
        this.workerJob_Evaluation = workerJob_Evaluation;
        this.workerFirst_WorkingDate = workerFirst_WorkingDate;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || worker name: %s || worker Job evaluation: %d|| worker first working date: %s", id, workerName, workerJob_Evaluation, workerFirst_WorkingDate);
    }
}