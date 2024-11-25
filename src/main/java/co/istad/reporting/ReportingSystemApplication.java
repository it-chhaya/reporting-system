package co.istad.reporting;

import co.istad.reporting.features.report.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReportingSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReportingSystemApplication.class, args);
    }

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        productRepository
                .findAll()
                .forEach(System.out::println);
    }
}
