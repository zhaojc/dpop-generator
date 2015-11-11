package org.mybatis.newgenerator.core.plugins.impl;

import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.mybatis.newgenerator.common.contents.Contents;
import org.mybatis.newgenerator.common.contents.Messages;
import org.mybatis.newgenerator.core.plugins.AbstractGeneratorPugin;
import org.mybatis.newgenerator.core.plugins.GeneratorModelData;
import org.mybatis.newgenerator.utils.Logger;


/**
 * 代码生成插件（最核心的逻辑）
 * 
 * */
public class DpopCoderGeneratorPlugin extends AbstractGeneratorPugin {

	private static DpopCoderGeneratorPlugin instance;
	private static VelocityEngine velocityEngine ;
	
	private DpopCoderGeneratorPlugin(){
		velocityEngine = new VelocityEngine();
		
		velocityEngine.setProperty(Velocity.RESOURCE_LOADER, "class");
		velocityEngine.setProperty(Velocity.VM_LIBRARY, "org/mybatis/newgenerator/template/commom.vm");
		velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		velocityEngine.setProperty(Velocity.OUTPUT_ENCODING, Contents.GLOBAL_CHAR_SET_ENCODING);
		try {
			velocityEngine.init();
		} catch (Exception e) {
			 Logger.writeLine(Messages.getString("ValidationError.4"));
		}
		
	}
	
	public static DpopCoderGeneratorPlugin getInstance (){
		if(null == instance){
			instance = new DpopCoderGeneratorPlugin();
		}
		return instance;
	}
	

	@Override
	public void startGenerator() {
		List<GeneratorModelData>  modelDatas = this.parse(config);
		for(GeneratorModelData modelData : modelDatas ){
			String canGenerators = modelData.getGenerators();
			if(canGenerator(canGenerators,Contents.GENERATORS[0])){
				this.generatorBO(modelData);
			}
			if(canGenerator(canGenerators,Contents.GENERATORS[1])){
				this.generatorBaseBO(modelData);
			}
			if(canGenerator(canGenerators,Contents.GENERATORS[2])){
				this.generatorMapper(modelData);
			}
			if(canGenerator(canGenerators,Contents.GENERATORS[3])){
				this.generatorMapperXML(modelData);
			}
			if(canGenerator(canGenerators,Contents.GENERATORS[4])){
				this.generatorBaseMapperXML(modelData);
			}
			if(canGenerator(canGenerators,Contents.GENERATORS[5])){
				this.generatorDaoInterface(modelData);
			}
			
			if(canGenerator(canGenerators,Contents.GENERATORS[6])){
				this.generatorDaoImpl(modelData);
			}
			
			if(canGenerator(canGenerators,Contents.GENERATORS[7])){
				this.generatorServiceInterface(modelData);
			}
			
			if(canGenerator(canGenerators,Contents.GENERATORS[8])){
				this.generatorServiceImpl(modelData);
			}
			
            if(canGenerator(canGenerators,Contents.GENERATORS[9])){
                this.generatorController(modelData);
            }	
            
            if(canGenerator(canGenerators,Contents.GENERATORS[10])){
                this.generatorForm(modelData);
            }     
            
            if(canGenerator(canGenerators,Contents.GENERATORS[11])){
                this.generatorValidator(modelData);
            }         
		}
	}
	
	
	private boolean canGenerator(String canGenerators,String generatorTag){
	    if(null== canGenerators || canGenerators.trim().isEmpty()){
	        return true;
	    }
	    
	    String[] canGeneratorsTagArr = canGenerators.split(",");
	    for(String tag : canGeneratorsTagArr){
	        if(tag.equals(generatorTag)){
	            return true;
	        }
	    }
	    return false;
	}
	

	
	@Override
	public String generator(GeneratorModelData modelData, String templet) {
		try {
			// 加载模板，设定模板编码
			Template template = velocityEngine.getTemplate(templet, "UTF-8");
			VelocityContext context = new VelocityContext();
			context.put(Contents.VELOCTIY_CONFIG_KEY, modelData);
			// 设置输出
			StringWriter writer = new StringWriter();
			// 将环境数据转化输出
			template.merge(context, writer);
			return writer.toString();
		} catch (ResourceNotFoundException e) {
			Logger.writeLine(Messages.getString("RuntimeError.0",templet));
		} catch (ParseErrorException e) {
			Logger.writeLine(Messages.getString("RuntimeError.1",templet));
		} catch (Exception e) {
			Logger.writeLine(Messages.getString("RuntimeError.2",templet));
		}
		return "";
	}
	
	
	
	
	private void generatorBaseBO(GeneratorModelData modelData){
		String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/dao/bo-base.vm");
		String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
				modelData.getBoPackage(),
				modelData.getDbtable().getBoName()+"Base.java");
		
		writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
	private void generatorBO(GeneratorModelData modelData){
		String coder =  generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/dao/bo.vm");
		String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
				modelData.getBoPackage(),
				modelData.getDbtable().getBoName()+".java");
		
		writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
	public void generatorMapper(GeneratorModelData modelData){
		 String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/dao/mapper.vm");
		 String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
					modelData.getMapperPackage(),
					modelData.getDbtable().getBoName()+"Mapper.java");
			
		 writeGeneratorCoder(coder,desFileAbsolutePath);
	}

	public void generatorMapperXML(GeneratorModelData modelData){
		 String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/dao/mapperxml.vm");
		 String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
					modelData.getMapperPackage(),
					modelData.getDbtable().getBoName()+"Mapper.xml");
			
		 writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
	public void generatorBaseMapperXML(GeneratorModelData modelData){
		String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/dao/mapperxml-base.vm");
		String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
					modelData.getMapperPackage(),
					modelData.getDbtable().getBoName()+"BaseMapper.xml");
		 writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
	
	public void  generatorDaoInterface(GeneratorModelData modelData){
		String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/dao/dao-interface.vm");
		String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
				modelData.getDaoPackage(),
				modelData.getDbtable().getBoName()+"Dao.java");
		writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
	public void generatorDaoImpl(GeneratorModelData modelData){
		String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/dao/dao-impl.vm");
		String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
				modelData.getDaoImplPackage(),
				modelData.getDbtable().getBoName()+"DaoImpl.java");
		writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
	public void generatorServiceInterface(GeneratorModelData modelData){
		String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/service/service-interface.vm");
		String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
				modelData.getServicePackage(),
				modelData.getDbtable().getBoName()+"Service.java");
		writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
	public void generatorServiceImpl(GeneratorModelData modelData){
		String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/service/service-impl.vm");
		String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
				modelData.getServiceImplPackage(),
				modelData.getDbtable().getBoName()+"ServiceImpl.java");
		writeGeneratorCoder(coder,desFileAbsolutePath);
	}
	
    public void generatorController(GeneratorModelData modelData){
        String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/web/controller.vm");
        String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
                modelData.getControllerPackage(),
                modelData.getDbtable().getBoName()+"Controller.java");
        writeGeneratorCoder(coder,desFileAbsolutePath);
    }	
	
    public void generatorForm(GeneratorModelData modelData){
        String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/web/form.vm");
        String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
                modelData.getControllerPackage(),
                modelData.getDbtable().getBoName()+"Form.java");
        writeGeneratorCoder(coder,desFileAbsolutePath);
    }
    
    public void generatorValidator(GeneratorModelData modelData){
        String coder = generator(modelData,Contents.GLOBAL_DPOP_TEMPLATE_ROOT+"/web/validator-info.vm");
        String desFileAbsolutePath = productDesFileAbsolutePath(modelData.getTargetPath(),
                modelData.getValidatorPackage(),
                "validation-info.properties");
        writeGeneratorCoder(coder,desFileAbsolutePath);
    }
    
}
