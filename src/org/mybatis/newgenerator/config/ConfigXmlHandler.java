package org.mybatis.newgenerator.config;

import org.mybatis.newgenerator.config.interfaces.IConfig;
import org.mybatis.newgenerator.config.interfaces.IXmlHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author huhailiang
 * @date 2014-6-29下午3:47:50
 */
public class ConfigXmlHandler extends DefaultHandler implements IXmlHandler {

	private Config xmlConfig=null;
	
	private ConfigItem currentItem=null;
	
	
	public IConfig getXmlConfig(){
		return xmlConfig;
	}
	
	/**
	 */
	public void startDocument() throws SAXException {
		xmlConfig= new Config();
	}
	
	/**
	 * 开始文档解析
	 */
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		String lableName = name.toLowerCase().trim();
		//如果是配置文件标识projects
		if(Config.configDivStart.equals(lableName)){
			for(int i=0;i<attributes.getLength();i++){
				String lableKeyName=attributes.getQName(i);
				String lableKeyValue=attributes.getValue(lableKeyName);
				xmlConfig.setAttribute(lableKeyName, lableKeyValue);
			}
		} else {
			if (lableName.startsWith(Config.configItemsDivStart)) {
				currentItem = (null == currentItem) ? new ConfigItem(): currentItem;
				currentItem.setItemType(lableName);
				
				//解析 
				for(int i=0;i<attributes.getLength();i++){
					String lableKeyName=attributes.getQName(i);
					String lableKeyValue=attributes.getValue(lableKeyName);
					currentItem.setAttribute(lableKeyName, lableKeyValue);
				}
				
			}else{
				//解析<property>属性
				for (int i = 0; i < attributes.getLength(); i+=2) {
					String lableKeyName = attributes.getQName(i);
					String lableKeyValue = attributes.getValue(lableKeyName);

					String lableValueName = attributes.getQName(i + 1);
					String lableValueValue = attributes.getValue(lableValueName);
					currentItem.setAttribute(lableKeyValue.trim(), lableValueValue);
				}
			}


		}

		
		
	}
	
	/**
	 * 
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
	}



	/**
	 * 
	 */
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		String lableName = name.toLowerCase().trim();
		if(lableName.startsWith(Config.configItemsDivStart)){
			xmlConfig.addItem(currentItem);
			currentItem = null;
		}
	}


	/**
	 */
	public void endDocument() throws SAXException {
		super.endDocument();
	}

}
