package org.mybatis.newgenerator.mains;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class TestDatabaseMetaData {

	private final static String DB_URL = "jdbc:mysql://10.46.133.67:8866/os_test";

	private final static String DB_DRIVER = "com.mysql.jdbc.Driver";

	private final static String DB_USERNAME = "crm";

	private final static String DB_PASSWORD = "123456";
	
	
	public static void main(String[] args) throws Exception{
		Connection connection = getConnection();
		DatabaseMetaData dbMetaData = connection.getMetaData();
		
		String[] types = new String[1];
		types[0]="TABLE";
		ResultSet tablesResultSet  = null;
		try{
			tablesResultSet  = dbMetaData.getTables(connection.getCatalog(), null, null, types);
			while(tablesResultSet.next()){
				String tableName = tablesResultSet.getString("TABLE_NAME");
				String tableREMARKS = tablesResultSet.getString("REMARKS"); 
				System.out.println("tableName:"+tableName+"--->"+tableREMARKS);
				ResultSet columnsResultSet  = null;
				ResultSet pkColumnsResultSet  = null;
				
				pkColumnsResultSet = dbMetaData.getPrimaryKeys(connection.getCatalog(), null, tableName);
				Set<String> pkSet = new HashSet<String>(4);
				
				while(pkColumnsResultSet.next()){
					String pk = pkColumnsResultSet.getString("COLUMN_NAME");
					System.out.println(pk);
					pkSet.add(pk);
				}
				
				try{
					System.out.println("+++++++++++++colums+++++++");
					columnsResultSet  = dbMetaData.getColumns(connection.getCatalog(), null, tableName, null);
					
					while(columnsResultSet.next()){
						
						String columName= columnsResultSet.getString("COLUMN_NAME");
						String columREMARKS= columnsResultSet.getString("REMARKS");
						String columCOLUMN_DEF= columnsResultSet.getString("COLUMN_DEF");
						String columNULLABLE = "";
						int columDATA_TYPE= columnsResultSet.getInt("DATA_TYPE");
						String columTYPE_NAME= columnsResultSet.getString("TYPE_NAME");
						
						System.out.println(String.format("name:%s,remark:%s,def:%s,nullable:%s,type:%d,typeName:%s", 
								columName,columREMARKS,
								columCOLUMN_DEF,columNULLABLE,
								columDATA_TYPE,columTYPE_NAME));
					}
				}finally{
					closeResultSet(columnsResultSet);
				}
				System.out.println("------------------");
			}
		}finally{
			closeResultSet(tablesResultSet);
		}

	}
	
	
	public static Connection getConnection(){
	   Connection conn = null;
	   try{
		   Class.forName(DB_DRIVER);
		   conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
	   }catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return conn;
	}
	
	public static void closeConnection(Connection conn){
	   try{
	    if(conn!=null){
	     if(!conn.isClosed()){
	      conn.close();
	     }
	    }
	   }catch(Exception ex){
	    ex.printStackTrace();
	   }
	}
	
	public  static void closeResultSet(ResultSet resultSet){
		   try{
			    if(resultSet!=null){
			     if(!resultSet.isClosed()){
			    	 resultSet.close();
			     }
			    }
			   }catch(Exception ex){
			    ex.printStackTrace();
			   }
	}
	

}
