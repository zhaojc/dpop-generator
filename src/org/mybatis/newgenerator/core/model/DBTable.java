package org.mybatis.newgenerator.core.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author huhailiang
 * @date 2014-6-26 下午9:39:20
 * @calss org.mybatis.newgenerator.core.model.DBTable.java
 * @todo 是数据库中数据表的建模
 */
public class DBTable {

	/**
	 * 模块名称，java项目中的模块名称
	 */
	protected String moduleName;
	
	/**
	 * java项目中的bo名称
	 */
	protected String boName;
	
	/**
	 * 数据库中表名称
	 */
    protected String dbTableName;
    
    /**
     * 字段备注
     */
    protected String remarks;
    
	/**
	 * 表的主键字段
	 */
    protected List<DBTableColumn> primaryKeyColumns ;
    
    /**
     * 表的其他字段
     */
    protected List<DBTableColumn> baseColumns = new ArrayList<DBTableColumn>(15);

    
    public void addColumn(DBTableColumn column ,boolean isPk){
    	primaryKeyColumns = (null == primaryKeyColumns)?new ArrayList<DBTableColumn>(3):primaryKeyColumns;
    	baseColumns = (null == baseColumns)?new ArrayList<DBTableColumn>(10):baseColumns;
    	
    	if(isPk){
    	    column.setNullable(false);
    		primaryKeyColumns.add(column);
    	}else{
    		baseColumns.add(column);
    	}
    	
    }
    
	public String getDbTableName() {
		return dbTableName;
	}

	public void setDbTableName(String dbTableName) {
		this.dbTableName = dbTableName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<DBTableColumn> getPrimaryKeyColumns() {
		return primaryKeyColumns;
	}

	public void setPrimaryKeyColumns(List<DBTableColumn> primaryKeyColumns) {
		this.primaryKeyColumns = Collections.unmodifiableList(primaryKeyColumns);
	}

	public List<DBTableColumn> getBaseColumns() {
		return baseColumns;
	}

	public void setBaseColumns(List<DBTableColumn> baseColumns) {
		this.baseColumns = Collections.unmodifiableList(baseColumns);;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}
    
}
