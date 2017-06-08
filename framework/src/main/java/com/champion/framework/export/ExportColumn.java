/**
 * @Description: 
 * @myPoi.cn.com.yeemin.poi
 * @FileName:ExportColumn.java
 * @Author: William
 * @CreateTime: 2017-03-25 10:45:53
 */
package com.champion.framework.export;

/**
 * @Description: 导出数据列
 * @ClassName: ExportColumn
 * @Auther: William
 * @CreateTime: 2017-03-25 10:45:53
 */
public class ExportColumn {
	
	/**
	 * 排序种子
	 */
	private int sort;
	
	/**
	 * 当前列对应的导出数据实体类的属性
	 */
	private String value;
	
	/**
	 * 当前列的表头名
	 */
	private String text;
	
	public ExportColumn() {}
	
	public ExportColumn(String value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
