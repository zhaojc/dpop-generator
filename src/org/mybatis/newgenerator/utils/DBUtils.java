package org.mybatis.newgenerator.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.mybatis.newgenerator.common.contents.Messages;
import org.mybatis.newgenerator.config.interfaces.IConfigItem;

/**
 * 
 * ClassName: DBUtils <br/>
 * date: 2014-8-6 上午3:41:00 <br/>
 *
 * @author huhailiang
 * @version 
 * @since JDK 1.6
 */
public abstract class DBUtils {

	public static Connection getConnection(IConfigItem configItem) throws Exception{
		   if(configItem == null){
			   Logger.writeLine(Messages.getString("ValidationError.0", "project:driver>"));
			   throw new RuntimeException();
		   }
		   String driverClass = configItem.getAttribute("driverClass");
		   String connectionURL = configItem.getAttribute("connectionURL");
		   String username = configItem.getAttribute("username");
		   String password = configItem.getAttribute("password");
		   
		   Connection conn = null;
		   try{
			   Class.forName(driverClass);
			   conn = DriverManager.getConnection(connectionURL,username,password);
		   }catch(Exception ex){
			   Logger.writeLine(Messages.getString("ValidationError.1"));
			   throw ex;
		   }
		   return conn;
	}
	
	
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				if (!conn.isClosed()) {
					conn.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				if (!resultSet.isClosed()) {
					resultSet.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

