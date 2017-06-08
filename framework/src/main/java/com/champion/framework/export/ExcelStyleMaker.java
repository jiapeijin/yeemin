package com.champion.framework.export;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/8 0008 16:56
 */
public class ExcelStyleMaker {

    /**
     * 表头样式名
     */
    private static final String HEADER_STYLE = "headerStyle";
    /**
     * 表体样式名
     */
    private static final String BODY_STYLE = "bodyStyle";

    public static void initStyle(Map<String, HSSFCellStyle> styleMap, HSSFWorkbook workbook) {
        styleMap = new HashMap<String, HSSFCellStyle>();
        /**
         * 表头样式，灰色前景色
         */
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        /**
         * 表头文字样式
         */
        HSSFFont headerFont = workbook.createFont();
        headerFont.setColor(HSSFColor.BLACK.index);
        headerFont.setFontHeightInPoints((short) 12);
        // 把字体应用到样式
        headerStyle.setFont(headerFont);

        /**
         * 表体样式
         */
        HSSFCellStyle bodyStyle = workbook.createCellStyle();
        /**
         * 表体文字样式
         */
        HSSFFont bodyFont = workbook.createFont();
        bodyFont.setColor(HSSFColor.BLACK.index);
        bodyFont.setFontHeightInPoints((short) 12);
        // 把字体应用到样式
        bodyStyle.setFont(bodyFont);

        styleMap.put(HEADER_STYLE, headerStyle);
        styleMap.put(BODY_STYLE, bodyStyle);
    }
}
