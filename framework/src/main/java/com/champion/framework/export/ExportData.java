/**
 * @Description: 
 * @myPoi.cn.com.yeemin.poi
 * @FileName:ExportDatas.java
 * @Author: William
 * @CreateTime: 2017-03-25 10:59:56
 */
package com.champion.framework.export;

import java.util.Collection;
import java.util.List;

/**
 * @Description: 待导出数据
 * @ClassName: ExportDatas
 * @Auther: William
 * @CreateTime: 2017-03-25 10:59:56
 */
public class ExportData {
	
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
	
	/**
	 * 
	 * @Description: 构造方法，设置默认日期显示格式 
	 * @Params: 
	 * @Author: William
	 * @CreateTime: 2017-03-25 11:03:13
	 */
	public ExportData() {
		this.pattern = "yyyy-MM-dd hh:mm:ss";
	}
	
	/**
	 * 
	 * @Description:构造方法 
	 * @Params: @param headers: 表头名称
	 * @Params: @param bodys: 表体单元格对应的类属性
	 * @Params: @param dataset: 数据
	 * @Author: William
	 * @CreateTime: 2017-03-25 11:09:27
	 */
	public ExportData(List<String> headers, List<String> bodys, Collection<?> dataset) {
		this.headers = headers;
		this.bodys = bodys;
		this.dataset = dataset;
		this.pattern = "yyyy-MM-dd hh:mm:ss";
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
