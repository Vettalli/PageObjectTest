package helpers;

import elements.Form;
import org.openqa.selenium.By;

public class TableUtil {
    public static Integer countNumberOfFilledRows(String rowsXpath, String cellXpath, Integer indexDifference){
        var amountOfRecords = 0;
        var rows = new Form(By.xpath(rowsXpath), "Rows");
        var rowsElements = rows.getElements();
        var cellIndex = 1;

        for (var row: rowsElements) {
            var element = row.findElement(By.xpath(StringUtil.getXpathWithNumberParam(cellXpath, cellIndex)));

            if(!element.getText().equals(" ")){
                amountOfRecords++;
            }

            cellIndex += indexDifference;
        }

        return amountOfRecords;
    }
}
