package org.mybatis.newgenerator.config.interfaces;

import java.util.List;

/**
 * 
 * 配置，由一些列配置项组成
 * 
 * @author huhailiang
 *
 */
public interface IConfig extends java.io.Serializable{

	
	
	/**
	 * 依据配置项id，将配置项放入配置中
	 * 
	 * @return
	 */
	public void addItem(IConfigItem item);
	
	/**
	 * 获取配置的所有配置项列表
	 * @return
	 */
	public List<IConfigItem> getAllItems();
	
	
	/**
	 * 根据属性名获取数据值
	 * 
	 * @return
	 */
	public String getAttribute(String key);
	
	
	/**
	 * 根据属性名获取数据值
	 * 
	 * @return
	 */
	public  void setAttribute(String key,String value);
	
}
