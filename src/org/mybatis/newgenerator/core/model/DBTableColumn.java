package org.mybatis.newgenerator.core.model;

/**
 * 
 * @author huhailiang
 * @date 2014-6-26 下午9:32:32
 * @calss org.mybatis.newgenerator.core.model.TableColumn.java
 * @todo TODO
 */
public class DBTableColumn {

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
	 * 数据库中表字段名称
	 */
    protected String dbTableColumnName;
    
    /**
     * java BO 中对应的属性名称
     */
    protected String javaBeanFieldName;

    /**
     * a value from java.sql.Types
     * 字段类型在JDBC里面的类型值
     */
    protected int jdbcType;

    /**
     * 字段类型在JDBC里面的类型名称
     */
    protected String jdbcTypeName;
    
    /**
     * 字段类型在JAVA里面的类型名称
     */
    protected String javaTypeName;

    /**
     * 字段是否可以为空
     */
    protected boolean nullable;
    
    /**
     * 字段备注
     */
    protected String remarks;

    /**
     * 字段默认值
     */
    protected String defaultValue;
    
    /**
     * 字段长度
     */
    protected Integer length;
  
    
    public String  toUpperCaseFieldName(){
    	StringBuffer sb = new StringBuffer(javaBeanFieldName);
    	sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
    	return sb.toString(); 
    }

	public String getDbTableColumnName() {
		return dbTableColumnName;
	}

	public void setDbTableColumnName(String dbTableColumnName) {
		this.dbTableColumnName = dbTableColumnName;
	}

	public String getJavaBeanFieldName() {
		return javaBeanFieldName;
	}

	public void setJavaBeanFieldName(String javaBeanFieldName) {
		this.javaBeanFieldName = javaBeanFieldName;
	}

	public int getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(int jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getJdbcTypeName() {
		return jdbcTypeName;
	}

	public void setJdbcTypeName(String jdbcTypeName) {
		this.jdbcTypeName = jdbcTypeName;
	}

	public String getJavaTypeName() {
		return javaTypeName;
	}

	public void setJavaTypeName(String javaTypeName) {
		this.javaTypeName = javaTypeName;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

    
    public Integer getLength() {
        return length;
    }

    
    public void setLength(Integer length) {
        this.length = length;
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

    
    public String getDbTableName() {
        return dbTableName;
    }

    
    public void setDbTableName(String dbTableName) {
        this.dbTableName = dbTableName;
    }
    
    
}
