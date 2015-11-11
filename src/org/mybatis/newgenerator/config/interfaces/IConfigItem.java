package org.mybatis.newgenerator.config.interfaces;

import java.util.Map;

/**
 * 配置项，配置的原子单位，由一些列的属性组成
 * @author huhailiang
 * @date 2014-6-27下午1:23:19
 */
public interface IConfigItem extends java.io.Serializable{

	/**
	 * 获取唯一标识符
	 * 
	 * @return
	 */
	public String getItemType();
	
	
	public void setItemType(String ttemType);
	
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
	
	/**
	 * 获取所有的属性名值映射表
	 * 
	 * @return
	 */
	public Map<String,String> getAttributes();
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasAttributeKey(String key);
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean hasAttributeValue(String value);
}