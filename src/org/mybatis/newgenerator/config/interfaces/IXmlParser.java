package org.mybatis.newgenerator.config.interfaces;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;

/**
 * 
 * @author huhailiang
 * @date 2014-6-27下午3:48:07
 */
public interface IXmlParser {

	 public <E extends IConfig> E parse(String content, IXmlHandler handler)throws SAXException, IOException, Exception ;
	 
	 public <E extends IConfig> E parse(InputStream inputStream, IXmlHandler handler) throws SAXException, IOException, Exception ;
	 
}
