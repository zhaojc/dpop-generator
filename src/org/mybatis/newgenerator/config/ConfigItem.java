package org.mybatis.newgenerator.config;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.newgenerator.config.interfaces.IConfigItem;

/**
 * 
 * @author huhailiang
 * @date 2014-6-27下午1:30:59
 */
public class ConfigItem implements IConfigItem {

	private static final long serialVersionUID = -2973750906746978211L;

	private Map<String, String> attributes=new HashMap<String , String>();
	
	private String type="";

	@Override
	public String getAttribute(String key) {
		return attributes.get(key);
	}
	@Override
	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}
	@Override
	public Map<String, String> getAttributes() {
		return this.attributes;
	}
	
	@Override
	public boolean hasAttributeKey(String key) {
		return attributes.containsKey(key);
	}
	
	@Override
	public boolean hasAttributeValue(String value) {
		return attributes.containsValue(value);
	}

	@Override
	public String getItemType() {
		return this.type;
	}

	@Override
	public void setItemType(String itemType) {
		this.type = itemType;
	}



}
