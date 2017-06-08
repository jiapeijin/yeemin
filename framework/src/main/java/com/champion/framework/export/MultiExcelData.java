/**
 * @Description: 
 * @myPoi.cn.com.yeemin.poi
 * @FileName:YPExcelData.java
 * @Author: William
 * @CreateTime: 2017-03-27 11:54:54
 */
package com.champion.framework.export;

import java.util.Collection;
import java.util.List;

/**
 * @Description: 导出Excel数据工作表
 * @ClassName: YPExcelData
 * @Auther: William
 * @CreateTime: 2017-03-27 11:54:54
 */
public class MultiExcelData {

	/**
	 * sheet名称
	 */
	private String title;
	
	/**
	 * 工作表表头
	 */
	private List<String> headers;
	
	/**
	 * 工作表表体数据来源
	 */
	private List<String> bodys;
	
	/**
	 * 表格数据
	 */
	private Collection<?> dataset;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
}
