package org.mybatis.newgenerator.config.interfaces;

/**
 * 
 * @author huhailiang
 * @date 2014-6-29下午3:48:18
 */
public interface IXmlHandler {

	public <E extends IConfig> E getXmlConfig();
}
