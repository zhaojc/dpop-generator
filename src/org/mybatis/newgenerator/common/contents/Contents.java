package org.mybatis.newgenerator.common.contents;

/**
 * 
 * @author huhailiang
 * @date 2014-6-28上午6:03:25
 */
public class Contents {

    public static final String GLOBAL_CHAR_SET_ENCODING="UTF-8";
    
    public static final String GLOBAL_DPOP_TEMPLATE_ROOT="org/mybatis/newgenerator/template/dpop";
    
	public static final String CONIG_TABLE_NAME_KEY = "tablename";
	
	public static final String CONIG_MODULE_NAME_KEY = "modulename";
	
	public static final String CONIG_BO_NAME_KEY = "boname";
	
	
	public static final String CONIG_ROOT_PACKAGE_KEY = "rootPackage";
	
	public static final String CONIG_TARGET_PATH_KEY = "targetPath";
	
	public static final String CONIG_BO_PACKAGE_KEY = "boPackage";
	
	public static final String CONIG_MAPPER_PACKAGE_KEY = "mapperPackage";
	
	public static final String CONIG_DAO_PACKAGE_KEY = "daoPackage";
	
	public static final String CONIG_DAO_IMPL_PACKAGE_KEY = "daoImplPackage";
	
	public static final String CONIG_SERVICE_PACKAGE_KEY = "servicePackage";
	
	public static final String CONIG_SERVICE_IMPL_PACKAGE_KEY = "serviceImplPackage";
	
	public static final String CONIG_CONTROLLER_PACKAGE_KEY = "controllerPackage";
	   	   
	public static final String CONIG_VALIDATOR_PACKAGE_KEY = "validatorPackage";
	
	public static final String CONIG_GENERATOR_KEY = "generators";
	
	
	
	public static final String ResultSet_REMARKS_KEY = "REMARKS";
	
	public static final String ResultSet_COLUMN_NAME_KEY = "COLUMN_NAME";
	
	public static final String ResultSet_COLUMN_DEF_KEY = "COLUMN_DEF";
	
	public static final String ResultSet_DATA_TYPE_KEY = "DATA_TYPE";
	
	public static final String ResultSet_TYPE_NAME_KEY = "TYPE_NAME";
	
	public static final String ResultSet_CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
	
    public static final String ResultSet_NULLABLE = "NULLABLE";	
	
	
	
	public static final String VELOCTIY_CONFIG_KEY = "modelData";
	
	public static final String[] QUREY_TABLE_TYPES = new String[1];
	static{
		QUREY_TABLE_TYPES[0]="TABLE";
	}
	
	public static String[] GENERATORS = new String[12];
	static{
		GENERATORS[0] ="bo";
		GENERATORS[1] ="bo-base";
		GENERATORS[2] ="mapper";
		GENERATORS[3] ="mapperxml";
		GENERATORS[4] ="mapperxml-base";
		GENERATORS[5] ="dao";
		GENERATORS[6] ="dao-impl";
		GENERATORS[7] ="service";
		GENERATORS[8] ="service-impl";
	    GENERATORS[9] ="controller";
	    GENERATORS[10]="form";
	    GENERATORS[11]="validator";
	}
	
	
	
	
	
}
