import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataDriven {

    public ArrayList<String> getData(String headerName, String columnData) {

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        ArrayList<String> arrayList = new ArrayList<>();

        try {
            fis = new FileInputStream("Testcase.xlsx");
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        XSSFSheet sheet = workbook.getSheet("TestSheet");
        //Identify Testcases column by scanning the entire 1st row

        Iterator<Row> rows = sheet.iterator();
        Row row = rows.next();

        Iterator<Cell> cells = row.cellIterator();

        int rownumber = 0;
        while (cells.hasNext()) {
            Cell cell = cells.next();
            if(cell.getStringCellValue().equals(headerName)) {
                //catch column
                rownumber = cell.getColumnIndex();
                break;
            }
        }
        //Once column is indentified then, scan entire testcase column to identify purchase testcase row

        while(rows.hasNext()) {
            row = rows.next();
            if(row.getCell(rownumber).getStringCellValue().equals(columnData)) {
                cells = row.cellIterator();
                break;
            }
        }

        while (cells.hasNext()) {
            Cell cell = cells.next();
            if(cell.getCellType() == CellType.STRING) {
                arrayList.add(cell.getStringCellValue());
            } else if (cell.getCellType() == CellType.NUMERIC) {
                arrayList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
            }
        }

        return arrayList;
    }

    public Map getBody() {
        Map<String, Object> jsonAsMap = new HashMap<>();

        ArrayList<String> list = getData("Testcases", "RestAddBook");

        jsonAsMap.put("name", list.get(1));
        jsonAsMap.put("isbn", list.get(2));
        jsonAsMap.put("aisle", list.get(3));
        jsonAsMap.put("author", list.get(4));

        return jsonAsMap;

    }
}
