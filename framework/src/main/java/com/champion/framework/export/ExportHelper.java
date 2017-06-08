/**
 * @Description: 
 * @myPoi.cn.com.yeemin.poi
 * @FileName:ExportHelper.java
 * @Author: William
 * @CreateTime: 2017-03-25 08:40:29
 */
package com.champion.framework.export;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 导出excel文件工具类
 * @ClassName: ExportHelper
 * @Auther: William
 * @CreateTime: 2017-03-25 08:40:29
 */
public class ExportHelper {

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
	 * 工作表
	 */
	private HSSFSheet sheet;

	/**
	 * Excel名称
	 */
	private String title;

	/**
	 * 样式
	 */
	private Map<String, HSSFCellStyle> styleMap;

	/**
	 * 待导出数据
	 */
	private ExportData exportData;

	/**
	 * 表头，即表格第一行显示数据
	 */
	private List<String> headers;

	/**
	 * 表体数据属性
	 */
	private List<String> bodys;

	/**
	 * 表格数据
	 */
	private Collection<?> dataset;

	/**
	 * 表格时间格式
	 */
	private String pattern;

	public ExportHelper() {
	}

	/**
	 * 
	 * @Description: 构造方法
	 * @Params: @param title: 工作表名称
	 * @Params: @param data: 待导出数据
	 * @Author: William
	 * @CreateTime: 2017-03-25 11:04:28
	 */
	public ExportHelper(String title, ExportData exportData) {
		this(title, exportData.getHeaders(), exportData.getBodys(), exportData.getDataset(), exportData.getPattern());
	}

	/**
	 * 
	 * @Description: 构造方法
	 * @Params: @param title
	 * @Params: @param headers
	 * @Params: @param dataset
	 * @Author: William
	 * @CreateTime: 2017-03-25 09:21:47
	 */
	public ExportHelper(String title, List<String> headers, List<String> bodys, Collection<?> dataset) {
		this(title, headers, bodys, dataset, "yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * 
	 * @Description: 构造方法
	 * @Params: @param title
	 * @Params: @param headers
	 * @Params: @param dataset
	 * @Params: @param pattern
	 * @Author: William
	 * @CreateTime: 2017-03-25 09:21:59
	 */
	public ExportHelper(String title, List<String> headers, List<String> bodys, Collection<?> dataset, String pattern) {
		this.title = title;
		this.headers = headers;
		this.bodys = bodys;
		this.dataset = dataset;
		this.pattern = pattern;
		initialize();
	}

	/**
	 * 
	 * @Description: 初始化工作簿
	 * @Param:
	 * @ReturnType void
	 * @Author: William
	 * @CreateTime: 2017-03-25 09:24:32
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize() {
		workbook = new HSSFWorkbook();
		initStyle();
		sheet = workbook.createSheet(title);
		sheet.setDefaultColumnWidth(24);

		/**
		 * 设置表头
		 */
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(styleMap.get(HEADER_STYLE));
			HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
			cell.setCellValue(text);
		}

		/**
		 * 遍历集合数据，产生数据行
		 */
		Iterator<?> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			/**
			 * 首先新增一行
			 */
			index++;
			row = sheet.createRow(index);

			Object t = it.next();
			Class tCls = t.getClass();
			for (int i = 0; i < bodys.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(styleMap.get(BODY_STYLE));
				String bodyName = bodys.get(i);
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
						textValue = value.toString();
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
	public ExportHelper write(OutputStream os) throws IOException {
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
	public ExportHelper write(HttpServletResponse response, String fileName) throws IOException {
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
	public ExportHelper writeFile(String fileName) throws IOException {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, HSSFCellStyle> getStyleMap() {
		return styleMap;
	}

	public void setStyleMap(Map<String, HSSFCellStyle> styleMap) {
		this.styleMap = styleMap;
	}

	public ExportData getExportData() {
		return exportData;
	}

	public void setExportData(ExportData exportData) {
		this.exportData = exportData;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public List<String> getBodys() {
		return bodys;
	}

	public void setBodys(List<String> bodys) {
		this.bodys = bodys;
	}

	public Collection<?> getDataset() {
		return dataset;
	}

	public void setDataset(Collection<?> dataset) {
		this.dataset = dataset;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
