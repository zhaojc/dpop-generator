package org.mybatis.newgenerator.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.mybatis.newgenerator.config.interfaces.IConfig;
import org.mybatis.newgenerator.config.interfaces.IXmlHandler;
import org.mybatis.newgenerator.config.interfaces.IXmlParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author huhailiang
 * @date 2014-6-27下午1:28:56
 */
public class ConfigXmlParser implements IXmlParser{
	
    public IConfig parse(String content, IXmlHandler handler)
            throws SAXException, IOException, Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        StringReader reader = new StringReader(content);
        SAXParser saxParser = factory.newSAXParser();
        InputSource input = new InputSource(reader);
        DefaultHandler saxHandler=(DefaultHandler)handler;
        saxParser.parse(input, saxHandler);
        return handler.getXmlConfig();
    }

    
    public IConfig parse(InputStream inputStream, IXmlHandler handler)
            throws SAXException, IOException,Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        InputSource input = new InputSource(inputStream);
        DefaultHandler saxHandler=(DefaultHandler)handler;
        saxParser.parse(input, saxHandler);
        return handler.getXmlConfig();
    }

}
