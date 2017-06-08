/**
 * @Description: 
 * @myPoi.cn.com.yeemin.poi
 * @FileName:YPExcelHelper.java
 * @Author: William
 * @CreateTime: 2017-03-27 11:43:05
 */
package com.champion.framework.export;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description: 多sheet的excel导出
 * @ClassName: YPExcelHelper
 * @Auther: William
 * @CreateTime: 2017-03-27 11:43:05
 */
public class MultiExcelHelper {
	
	private final static Logger logger = LoggerFactory.getLogger(MultiExcelHelper.class);

	/**
	 * 表头样式名
	 */
	private final String HEADER_STYLE = "headerStyle";
	/**
	 * 表体样式名
	 */
	private final String BODY_STYLE = "bodyStyle";

	/**
	 * 工作簿
	 */
	private HSSFWorkbook workbook;

	/**
	 * 样式
	 */
	private Map<String, HSSFCellStyle> styleMap;
	
	/**
	 * 表格数据
	 */
	private List<MultiExcelData> excelDatas;
	
	/**
	 * 时间属性格式
	 */
	private String pattern;
	
	/**
	 * @Description: 
	 * @Params: 
	 * @Author: William
	 * @CreateTime: 2017-03-27 02:35:26
	 */

	public MultiExcelHelper() {
	}
	
	/**
	 * 
	 * @Description: 
	 * @Params: @param excelDatas
	 * @Author: William
	 * @CreateTime: 2017-03-27 02:36:35
	 */
	public MultiExcelHelper(List<MultiExcelData> excelDatas) {
		this.excelDatas = excelDatas;
		this.pattern = "yyyy-MM-dd hh:mm:ss";
		initialize();
	}
	/**
	 * 
	 * @Description: 初始化工作簿 
	 * @Param: 
	 * @ReturnType void
	 * @Author: William
	 * @CreateTime: 2017-03-27 11:46:10
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize() {
		logger.info("开始初始化excel");
		Long startTime = System.currentTimeMillis();
		workbook = new HSSFWorkbook();
		initStyle();
		if(excelDatas !=null && excelDatas.size() > 0){
		for(MultiExcelData excelData : excelDatas) {
			//创建sheet
			HSSFSheet sheet = null;
			if (StringUtils.isNoneBlank(excelData.getTitle())) {
				sheet = workbook.createSheet(excelData.getTitle());
			} else {
				sheet = workbook.createSheet("sheet");
			}
			
			//设置默认单元格显示长度
			sheet.setDefaultColumnWidth(24);
			//创建表头
			HSSFRow row = sheet.createRow(0);
			for (short i=0; i<excelData.getHeaders().size(); i++) {
				Cell cell = row.createCell(i);
				cell.setCellStyle(styleMap.get(HEADER_STYLE));
				HSSFRichTextString textValue = new HSSFRichTextString(excelData.getHeaders().get(i));
				cell.setCellValue(textValue);
			}
			
			if (excelData != null && excelData.getDataset().size() > 0) {
				Iterator<?> it = excelData.getDataset().iterator();
				int index = 0;
				while (it.hasNext()) {
					index ++;
					row = sheet.createRow(index);
					Object t = it.next();
					Class tCls = t.getClass();
					for (int i=0; i<excelData.getBodys().size(); i ++) {
						HSSFCell cell = row.createCell(i);
						cell.setCellStyle(styleMap.get(BODY_STYLE));
						String bodyName = excelData.getBodys().get(i);
						if (StringUtils.isNotEmpty(bodyName)) {
							String getMethodName = "get" + bodyName.substring(0, 1).toUpperCase() + bodyName.substring(1);
							
							try {
								Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
								Object value = getMethod.invoke(t, new Object[] {});
								String textValue = null;
								/**
								 * 判断值的类型后进行强制类型转换
								 */
								if (value instanceof Date) {
									// 时间类型
									Date date = (Date) value;
									SimpleDateFormat sdf = new SimpleDateFormat(pattern);
									textValue = sdf.format(date);
									cell.setCellValue(textValue);
								} else {
									// 其他类型，直接转字符串赋值
									textValue = value == null ? "" : value.toString();
									cell.setCellValue(textValue);
								}
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			
		}
	}
	Long endTime = System.currentTimeMillis();
	logger.info("初始化excel完成，共耗时" + (endTime - startTime) + "毫秒");
	}
	
	/**
	 * 
	 * @Description: 初始化表头表体样式
	 * @Param:
	 * @ReturnType void
	 * @Author: William
	 * @CreateTime: 2017-03-25 09:32:57
	 */
	public void initStyle() {
		ExcelStyleMaker.initStyle(styleMap, workbook);
	}
	
	
	/**
	 * 
	 * @Description: 输出到流
	 * @Param: @param os
	 * @Param: @return
	 * @Param: @throws IOException
	 * @ReturnType ExportHelper
	 * @Author: William
	 * @CreateTime: 2017-03-25 08:54:02
	 */
	public MultiExcelHelper write(OutputStream os) throws IOException {
		workbook.write(os);
		workbook = null;
		os.flush();
		os.close();
		return this;
	}

	/**
	 * 
	 * @Description:输出到客户端
	 * @Param: @param response
	 * @Param: @param fileName：文件名
	 * @Param: @return
	 * @Param: @throws IOException
	 * @ReturnType ExportHelper
	 * @Author: William
	 * @CreateTime: 2017-03-25 08:58:42
	 */
	public MultiExcelHelper write(HttpServletResponse response, String fileName) throws IOException {
		response.reset();
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + urlEncode(fileName));
		write(response.getOutputStream());
		return this;
	}

	/**
	 * 
	 * @Description:输出到文件
	 * @Param: @param fileName：文件名（包含完整路径）
	 * @Param: @return
	 * @Param: @throws IOException
	 * @ReturnType ExportHelper
	 * @Author: William
	 * @CreateTime: 2017-03-25 08:56:25
	 */
	public MultiExcelHelper writeFile(String fileName) throws IOException {
		FileOutputStream os = new FileOutputStream(fileName);
		this.write(os);
		return this;
	}
	
	/**
	 * 
	 * @Description:设置输出到客户端时的文件名编码 
	 * @Param: @param part
	 * @Param: @return
	 * @ReturnType String
	 * @Author: William
	 * @CreateTime: 2017-03-25 11:59:59
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
}
