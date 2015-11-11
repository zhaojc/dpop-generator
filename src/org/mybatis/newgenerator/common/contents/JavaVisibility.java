package org.mybatis.newgenerator.common.contents;

/**
 * 
 * @author huhailiang
 * @date 2014-6-26  8:08:26
 * @calss org.mybatis.newgenerator.common.contentsJavaVisibility.java
 * @todo TODO
 */
public enum JavaVisibility {
	
    PUBLIC("public "), 
    PRIVATE("private "), 
    PROTECTED("protected "), 
    DEFAULT(""); 

    private String value;

    private JavaVisibility(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
