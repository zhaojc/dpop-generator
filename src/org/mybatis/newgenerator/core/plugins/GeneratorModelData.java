package org.mybatis.newgenerator.core.plugins;

import org.mybatis.newgenerator.core.model.DBTable;

/**
 * 
 * @author huhailiang
 * @date 2014-6-27上午10:23:05
 */
public class GeneratorModelData {
	
	private DBTable dbtable;
	
	private String rootPackage;
	
	private String targetPath;
	
	private String boPackage ;
	
	private String mapperPackage;
	
	private String daoPackage;
	
	private String daoImplPackage;
	
	private String servicePackage;
	
	private String serviceImplPackage;
	
	private String controllerPackage;
	   
	private String validatorPackage;
	
	private String generators;

	public DBTable getDbtable() {
		return dbtable;
	}

	public void setDbtable(DBTable dbtable) {
		this.dbtable = dbtable;
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getBoPackage() {
		return boPackage;
	}

	public void setBoPackage(String boPackage) {
		this.boPackage = boPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoImplPackage() {
		return daoImplPackage;
	}

	public void setDaoImplPackage(String daoImplPackage) {
		this.daoImplPackage = daoImplPackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getGenerators() {
		return generators;
	}

	public void setGenerators(String generators) {
		this.generators = generators;
	}

    
    public String getControllerPackage() {
        return controllerPackage;
    }

    
    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    
    public String getValidatorPackage() {
        return validatorPackage;
    }

    
    public void setValidatorPackage(String validatorPackage) {
        this.validatorPackage = validatorPackage;
    }

}
