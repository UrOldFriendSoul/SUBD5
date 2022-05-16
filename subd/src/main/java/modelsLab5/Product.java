package modelsLab5;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_product")
    private int id;
    @Column(name = "product_name")
    private String product_name;
    @Column(name = "product_price")
    private int product_price;

    public Product(String product_name, int product_price) {
        this.product_name = product_name;
        this.product_price = product_price;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || product name: %s || product price: %d", id, product_name, product_price);
    }
}