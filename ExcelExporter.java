package JavaJava3;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import progect10.DHandler;

import java.io.FileOutputStream;
import java.sql.*;

public class ExcelExporter {
    public void export() {
        String sqlTables = "SHOW TABLES";
        try (Connection conn = DHandler.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rsTables = stmt.executeQuery(sqlTables)) {
            while (rsTables.next()) {
                String table = rsTables.getString(1);
                String query = "SELECT * FROM `" + table + "`";
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet(table);

                CellStyle headerStyle = workbook.createCellStyle();
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerStyle.setFont(headerFont);
                headerStyle.setBorderTop(BorderStyle.THIN);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);

                Row headerRow = sheet.createRow(0);
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = headerRow.createCell(i - 1);
                    cell.setCellValue(rsmd.getColumnName(i));
                    cell.setCellStyle(headerStyle);
                }

                int rowIndex = 1;
                while (resultSet.next()) {
                    Row row = sheet.createRow(rowIndex++);

                    for (int i = 1; i <= columnCount; i++) {
                        Cell cell = row.createCell(i - 1);
                        cell.setCellValue(resultSet.getString(i));
                        headerStyle.setFont(headerFont);
                        headerStyle.setBorderTop(BorderStyle.THIN);
                        headerStyle.setBorderBottom(BorderStyle.THIN);
                        headerStyle.setBorderLeft(BorderStyle.THIN);
                        headerStyle.setBorderRight(BorderStyle.THIN);
                    }
                }

                try (FileOutputStream out = new FileOutputStream("C:\\Users\\1\\Desktop\\JavaExcelTables\\" + table + ".xlsx")) {
                    workbook.write(out);
                }

                workbook.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
