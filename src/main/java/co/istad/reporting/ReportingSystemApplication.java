package co.istad.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class ReportingSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReportingSystemApplication.class, args);
    }

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(primaryDataSource.hashCode());
        System.out.println(secondaryDataSource.hashCode());
    }
}
