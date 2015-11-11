package org.mybatis.newgenerator.core.plugins;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.newgenerator.common.contents.Contents;
import org.mybatis.newgenerator.common.contents.Messages;
import org.mybatis.newgenerator.config.Config;
import org.mybatis.newgenerator.config.ConfigXmlHandler;
import org.mybatis.newgenerator.config.ConfigXmlParser;
import org.mybatis.newgenerator.config.interfaces.IConfigItem;
import org.mybatis.newgenerator.config.interfaces.IXmlParser;
import org.mybatis.newgenerator.core.model.DBTable;
import org.mybatis.newgenerator.core.model.DBTableColumn;
import org.mybatis.newgenerator.core.model.TypeNameTranslator;
import org.mybatis.newgenerator.utils.ColumnConvertUtil;
import org.mybatis.newgenerator.utils.DBUtils;
import org.mybatis.newgenerator.utils.Logger;
import org.mybatis.newgenerator.utils.PropertyPlaceholderUtils;

/**
 * 
 * @author huhailiang
 * @date 2014-6-27上午10:36:58
 */
public abstract class AbstractGeneratorPugin implements GeneratorPlugin {

	protected Config config ;
	protected Boolean overwrite = false ;
	protected static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	@Override
	public void initConfig(File configurationFile, boolean overwrite) {
		this.overwrite =  overwrite;
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(configurationFile);
		} catch (FileNotFoundException e) {
            Logger.writeLine(Messages.getString("RuntimeError.0", configurationFile.getName())); 
            return;
		}
		
		IXmlParser  xmlParser=new ConfigXmlParser();
		try {
			config = xmlParser.parse(inputStream, new ConfigXmlHandler());
		} catch (Exception e) {
            Logger.writeLine(Messages.getString("RuntimeError.4", configurationFile.getName())); 
            return;
		}
	}
	
	@Override
	public abstract void startGenerator();

	/**
	 * 
	 */
	public List<GeneratorModelData> parse(Config config){
		if(null == config){
			 Logger.writeLine(Messages.getString("ValidationError.2"));
		}
		Connection connection =  null;
		try{
			connection =DBUtils.getConnection(config.getDBdriverConfig());
		}catch(Exception e){
			Logger.writeLine(Messages.getString("ValidationError.1"));
			DBUtils.closeConnection(connection);
			return null;
		}
		
		List<IConfigItem> tableConfigs = config.getTableConfigs();
		if (null == tableConfigs || tableConfigs.size() == 0) {
			return new ArrayList<GeneratorModelData>(0);
		}

		List<GeneratorModelData> datas = new ArrayList<GeneratorModelData>(tableConfigs.size());
		try{
			for(IConfigItem configItem : tableConfigs){
				GeneratorModelData genData = parse(configItem,connection.getCatalog(),connection.getMetaData());
				//从Base配置继承配置
				extendsBasePropertys(genData,configItem,config.getBaseConfig());
				datas.add(genData);
			}
		}catch (SQLException e) {
			Logger.writeLine(Messages.getString("ValidationError.3"));
		}finally{
			DBUtils.closeConnection(connection);
		}
		return datas;
	}
	
	/**
	 * 从Base配置继承配置
	 * 
	 * @param genData
	 * @param configBase
	 */
	private void extendsBasePropertys(GeneratorModelData genData,IConfigItem tableConfig,IConfigItem configBase){
		if(null == configBase){
			return;
		}
		
		Map<String,String> newPropertyMap = new HashMap<String,String>();
		newPropertyMap.putAll(configBase.getAttributes());
		newPropertyMap.putAll(tableConfig.getAttributes());
		
		genData.setRootPackage(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_ROOT_PACKAGE_KEY), 
				newPropertyMap));
		
		genData.setTargetPath(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_TARGET_PATH_KEY), 
				newPropertyMap));
		
		genData.setGenerators(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_GENERATOR_KEY),
				newPropertyMap));
		
		genData.setBoPackage(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_BO_PACKAGE_KEY), 
				newPropertyMap));
		
		genData.setMapperPackage(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_MAPPER_PACKAGE_KEY), 
				newPropertyMap));
		
		genData.setDaoPackage(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_DAO_PACKAGE_KEY), 
				newPropertyMap));
		
		genData.setDaoImplPackage(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_DAO_IMPL_PACKAGE_KEY), 
				newPropertyMap));
		
		genData.setServicePackage(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_SERVICE_PACKAGE_KEY), 
				newPropertyMap));
		
		genData.setServiceImplPackage(PropertyPlaceholderUtils.replacePlaceholders(
				newPropertyMap.get(Contents.CONIG_SERVICE_IMPL_PACKAGE_KEY), 
				newPropertyMap));
		
		genData.setControllerPackage(PropertyPlaceholderUtils.replacePlaceholders(
                newPropertyMap.get(Contents.CONIG_CONTROLLER_PACKAGE_KEY), 
                newPropertyMap));
		
		genData.setValidatorPackage(PropertyPlaceholderUtils.replacePlaceholders(
                newPropertyMap.get(Contents.CONIG_VALIDATOR_PACKAGE_KEY), 
                newPropertyMap));
		
		
	}

	
	/**
	 * 
	 * @param configItem
	 * @param dbCatalog
	 * @param dbMetaData
	 * @return
	 */
	private GeneratorModelData parse(IConfigItem configItem,String dbCatalog,DatabaseMetaData dbMetaData){
		
		String tablename = configItem.getAttribute(Contents.CONIG_TABLE_NAME_KEY);
		String modulename = configItem.getAttribute(Contents.CONIG_MODULE_NAME_KEY);
		String boname = configItem.getAttribute(Contents.CONIG_BO_NAME_KEY);
		
		GeneratorModelData genData = new GeneratorModelData();
		DBTable dbtable = new DBTable();
		
		dbtable.setModuleName(modulename);
		if(null == boname || boname.trim().isEmpty()){
			boname = ColumnConvertUtil.columnName2FieldName(tablename);
		}
		dbtable.setBoName(boname);
		dbtable.setDbTableName(tablename);
		
		genData.setDbtable(dbtable);
		
		Set<String> pkSet = parseTablePkColumns(tablename,dbCatalog,dbMetaData);
		
		ResultSet tablesResultSet  = null;
		try{
			tablesResultSet  = dbMetaData.getTables(dbCatalog, null, tablename, Contents.QUREY_TABLE_TYPES);
			while(tablesResultSet.next()){
				dbtable.setRemarks(tablesResultSet.getString(Contents.ResultSet_REMARKS_KEY)); 
				ResultSet columnsResultSet  = null;
				try{
					columnsResultSet  = dbMetaData.getColumns(dbCatalog, null, tablename, null);
					while(columnsResultSet.next()){
						DBTableColumn dbTableColumn = new DBTableColumn();
						
						dbTableColumn.setDbTableName(tablename);
						dbTableColumn.setModuleName(modulename);
						dbTableColumn.setBoName(boname);
						
						String COLUMN_NAME= columnsResultSet.getString(Contents.ResultSet_COLUMN_NAME_KEY);
						dbTableColumn.setDbTableColumnName(COLUMN_NAME);
						
						String REMARKS= columnsResultSet.getString(Contents.ResultSet_REMARKS_KEY);
						dbTableColumn.setRemarks(REMARKS);
						
						String COLUMN_DEF= columnsResultSet.getString(Contents.ResultSet_COLUMN_DEF_KEY);
						dbTableColumn.setDefaultValue(COLUMN_DEF);
						
						int CHAR_OCTET_LENGTH = columnsResultSet.getInt(Contents.ResultSet_CHAR_OCTET_LENGTH);
						dbTableColumn.setLength(CHAR_OCTET_LENGTH);
						
						int DATA_TYPE= columnsResultSet.getInt(Contents.ResultSet_DATA_TYPE_KEY);
						dbTableColumn.setJdbcType(DATA_TYPE);
						
						int NULLABLE= columnsResultSet.getInt(Contents.ResultSet_NULLABLE);
						dbTableColumn.setNullable(0 != NULLABLE);
						
//						String TYPE_NAME= columnsResultSet.getString(Contents.ResultSet_TYPE_NAME_KEY);
						dbTableColumn.setJdbcTypeName(TypeNameTranslator.getJdbcTypeName(DATA_TYPE));
						dbTableColumn.setJavaBeanFieldName(ColumnConvertUtil.columnName2FieldName(COLUMN_NAME));
						dbTableColumn.setJavaTypeName(TypeNameTranslator.getJavaTypeName(DATA_TYPE));
				
						
						dbtable.addColumn(dbTableColumn, pkSet.contains(COLUMN_NAME));
					}
				}finally{
					DBUtils.closeResultSet(columnsResultSet);
				}
			}
			
		}catch(Exception e){
			Logger.writeLine(Messages.getString("ValidationError.3"));
		}finally{
			DBUtils.closeResultSet(tablesResultSet);
		}
		return genData;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param dbCatalog
	 * @param dbMetaData
	 * @return
	 */
	private Set<String> parseTablePkColumns(String tableName,String dbCatalog,DatabaseMetaData dbMetaData){
		ResultSet pkColumnsResultSet  = null;
		Set<String> pkSet = new HashSet<String>(2);
		try{
			pkColumnsResultSet = dbMetaData.getPrimaryKeys(dbCatalog, null, tableName);
			while(pkColumnsResultSet.next()){
				pkSet.add(pkColumnsResultSet.getString(Contents.ResultSet_COLUMN_NAME_KEY));
			}
		}catch(Exception e){
			Logger.writeLine(Messages.getString("ValidationError.3"));
		}finally{
			DBUtils.closeResultSet(pkColumnsResultSet);
		}
		return pkSet;
	}
	
	protected String productDesFileAbsolutePath(String targetPath,String javaPackage,String fileName){
		boolean targetPathEndWith =targetPath.endsWith(FILE_SEPARATOR); 
		String javaPackagePath = javaPackage.replace(".", FILE_SEPARATOR);
		
		StringBuffer desFileAbsolutePath = new StringBuffer();
		desFileAbsolutePath.append(targetPath);
		desFileAbsolutePath.append(targetPathEndWith?"":FILE_SEPARATOR);
		desFileAbsolutePath.append(javaPackagePath);
		desFileAbsolutePath.append(FILE_SEPARATOR);
		desFileAbsolutePath.append(fileName);
		return desFileAbsolutePath.toString();
		
	}
	
	protected void writeGeneratorCoder(String coder,String fileAbsolutePath){
		String fileAbsolutePathNew = this.overwrite? fileAbsolutePath : fileAbsolutePath+".new";
	    File file = new File(fileAbsolutePathNew);//创建多级目录文件  
	    BufferedWriter output = null;
	    try {
		    file.getParentFile().mkdirs();  
			file.createNewFile();
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),Contents.GLOBAL_CHAR_SET_ENCODING); 
		    output = new BufferedWriter(write);  
		    output.write(coder);  
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			if(null != output){
				try {
					output.flush();
					Logger.writeLine(fileAbsolutePath + " ---> generator sucess!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
			}
		}
	}
	
}
