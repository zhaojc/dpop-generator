package org.mybatis.newgenerator.core.plugins;

import java.io.File;
import java.util.List;

import org.mybatis.newgenerator.config.Config;

/**
 * 
 * @author huhailiang
 * @date 2014-6-27上午10:29:19
 */
public interface GeneratorPlugin {
	
	public void initConfig(File configurationFile,boolean overwrite);

	public void startGenerator();
	
	/**
	 * 
	 * @param config
	 * @return
	 */
	public List<GeneratorModelData> parse(Config config);
	
	/**
	 * 
	 * @param data
	 * @param templet
	 * @return
	 */
	public String generator(GeneratorModelData data,String templet);
}
