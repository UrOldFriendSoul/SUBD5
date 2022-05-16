package modelsLab5;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shift", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shift {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_shift")
    private int id;
    @Column(name = "shift_date")
    private String shift_date;
    @Column(name = "week_day")
    private String week_day;

    public Shift(String shift_date, String week_day) {
        this.shift_date = shift_date;
        this.week_day = week_day;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d ||shift date: %s || shift week day: %s", id, shift_date, week_day);
    }
}