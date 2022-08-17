package dev.mtj.excelfilegenerate.web;

import dev.mtj.excelfilegenerate.entity.Employee;
import dev.mtj.excelfilegenerate.serviceimpl.EmployeeImpl;
import dev.mtj.excelfilegenerate.serviceimpl.ExcelGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeServiceRController {
    public final  EmployeeImpl employee;

    public EmployeeServiceRController(EmployeeImpl employee) {
        this.employee = employee;
    }

    @GetMapping("/getAllEmployee/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {


        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Employee> listOfEmployee = employee.getAllEmployee();

        ExcelGenerator generator = new ExcelGenerator(listOfEmployee);

        generator.generate(response);
    }

  @GetMapping("/")
    public String Home(Model m){
        m.addAttribute("employees", employee.getAllEmployee());
        return "index";
  }
}
