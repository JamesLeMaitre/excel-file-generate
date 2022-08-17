package dev.mtj.excelfilegenerate;

import com.github.javafaker.Faker;
import dev.mtj.excelfilegenerate.entity.Employee;
import dev.mtj.excelfilegenerate.serviceimpl.EmployeeImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExcelFileGenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelFileGenerateApplication.class, args);
    }
    @Bean
    CommandLineRunner start(EmployeeImpl employees){
        return args->{

            for(int i = 0; i<10; i++){
                Faker faker = new Faker();
                Employee employee = new Employee();

                String name = faker.job().title();
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();

                employee.setJob(name);
                employee.setLastname(lastName);
                employee.setName(firstName);
                employees.addEmployee(employee);
            }
        };
    }
}
