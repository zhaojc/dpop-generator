package org.mybatis.newgenerator.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.newgenerator.config.interfaces.IConfig;
import org.mybatis.newgenerator.config.interfaces.IConfigItem;

/**
 * 
 * @author huhailiang
 * @date 2014-6-27下午1:30:55
 */
public class Config implements IConfig {

	private static final long serialVersionUID = -5062746062157640813L;

	public static final String  configItemsDivStart="project";
	
	public static final String configDivStart="generators";
	
	private static final String[] KEY = new String[]{"project:base","project:driver","project:table"};
	
	private List<IConfigItem> items = new LinkedList<IConfigItem>();
	
	private Map<String,List<IConfigItem>> itemsMap = new ConcurrentHashMap<String,List<IConfigItem>>();
	
	private Map<String,String> attributes = new ConcurrentHashMap<String,String>();
	
	public List<IConfigItem> getAllItems() {
		return Collections.unmodifiableList(items);
	}

	@Override
	public String getAttribute(String key) {
		return attributes.get(key);
	}

	@Override
	public void setAttribute(String key, String value) {
		attributes.put(key, value);
	}

	@Override
	public void addItem(IConfigItem item) {
		items.add(item);
	}
	
	public IConfigItem getBaseConfig(){
		List<IConfigItem> itemList = getConfigItem(KEY[0]);
		return (null == itemList) ? null : itemList.get(0);
	}
	
	public IConfigItem getDBdriverConfig(){
		List<IConfigItem> itemList = getConfigItem(KEY[1]);
		return (null == itemList) ? null : itemList.get(0);
	}

	public List<IConfigItem> getTableConfigs(){
		return getConfigItem(KEY[2]);
	}
	
	private List<IConfigItem> getConfigItem(String key){
		List<IConfigItem> itemsTemp = itemsMap.get(key);
		if(itemsTemp != null && itemsTemp.size()>0){
			return itemsTemp;
		}
		List<IConfigItem> itemList = new ArrayList<IConfigItem>();
		for(IConfigItem itemTemp : items){
			if(itemTemp.getItemType().equals(key)){
				itemList.add(itemTemp);
			}
		}
		itemsMap.put(key, itemList);
		return itemList;
	}
}
