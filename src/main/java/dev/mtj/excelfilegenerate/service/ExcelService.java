package dev.mtj.excelfilegenerate.service;

import dev.mtj.excelfilegenerate.entity.Employee;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ExcelService {
     void writeHeader();
    void createCell(Row row, int columnCount,
                    Object value, CellStyle style);
    void write();
    void generate(HttpServletResponse response) throws IOException;

}
