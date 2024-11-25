package co.istad.reporting.domain.oracle;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "products", schema = "app_user")
public class Product {


    @Id
    private Long id;
    private String name;
    private Double price;

}
