package dev.mtj.excelfilegenerate.serviceimpl;

import dev.mtj.excelfilegenerate.entity.Employee;
import dev.mtj.excelfilegenerate.service.ExcelService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator implements ExcelService {

    private  List<Employee> listEmployee;
    private  XSSFWorkbook workbook;
    private  XSSFSheet sheet;

    public ExcelGenerator(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
        workbook = new XSSFWorkbook();
    }

    @Override
    public void writeHeader() {
    sheet = workbook.createSheet("Liste des employées");
    Row row = sheet.createRow(0);

    CellStyle  style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row,0, "ID",style);
        createCell(row,1, "Name",style);
        createCell(row,2, "LastName",style);
        createCell(row,3, "Job",style);
    }

    @Override
    public void createCell(Row row,
                           int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    @Override
    public void write() {
int rowCount = 1;

    CellStyle style = workbook.createCellStyle();
    XSSFFont font = workbook.createFont();
    font.setFontHeight(14);
    style.setFont(font);

    for(Employee employee : listEmployee){
        Row row = sheet.createRow(rowCount++);
        int columnCount = 0;

        createCell(row, columnCount++,employee.getId(), style);
        createCell(row, columnCount++, employee.getName(),style);
        createCell(row, columnCount++,employee.getLastname(), style);

        createCell(row, columnCount++,employee.getJob(), style);
    }
    }

    @Override
    public void generate(HttpServletResponse response) throws IOException {
    writeHeader();
    write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
