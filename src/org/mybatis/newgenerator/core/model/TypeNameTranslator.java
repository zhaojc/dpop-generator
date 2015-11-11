package org.mybatis.newgenerator.core.model;

import java.sql.Date;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author huhailiang
 * @date 2014-6-26 ����8:53:06
 * @calss org.mybatis.newgenerator.core.model.TypeNameTranslator.java
 * @todo TODO
 */
public final class TypeNameTranslator {

    private static Map<String, Integer> jdbcTypeName2ValueMap;
    private static Map<Integer, String> jdbcTypeValue2NameMap;
    private static Map<Integer, String> jdbcTypeValue2JavaTypeNameMap;

    static {
    	Map<Integer, String> typeToName = new HashMap<Integer, String>(45);
        typeToName.put(Types.ARRAY, "ARRAY"); 
        typeToName.put(Types.BIGINT, "BIGINT"); 
        typeToName.put(Types.BINARY, "BINARY"); 
        typeToName.put(Types.BIT, "BIT"); 
        typeToName.put(Types.BLOB, "BLOB"); 
        typeToName.put(Types.BOOLEAN, "BOOLEAN"); 
        typeToName.put(Types.CHAR, "CHAR"); 
        typeToName.put(Types.CLOB, "CLOB"); 
        typeToName.put(Types.DATALINK, "DATALINK"); 
        typeToName.put(Types.DATE, "DATE"); 
        typeToName.put(Types.DECIMAL, "DECIMAL"); 
        typeToName.put(Types.DISTINCT, "DISTINCT"); 
        typeToName.put(Types.DOUBLE, "DOUBLE"); 
        typeToName.put(Types.FLOAT, "FLOAT"); 
        typeToName.put(Types.INTEGER, "INTEGER"); 
        typeToName.put(Types.JAVA_OBJECT, "JAVA_OBJECT"); 
        typeToName.put(Types.LONGVARBINARY, "LONGVARBINARY"); 
        typeToName.put(Types.LONGVARCHAR, "LONGVARCHAR"); 
        typeToName.put(Jdbc4Types.NCHAR, "NCHAR"); 
        typeToName.put(Jdbc4Types.NCLOB, "NCLOB"); 
        typeToName.put(Jdbc4Types.NVARCHAR, "NVARCHAR"); 
        typeToName.put(Jdbc4Types.LONGNVARCHAR, "LONGNVARCHAR"); 
        typeToName.put(Types.NULL, "NULL"); 
        typeToName.put(Types.NUMERIC, "NUMERIC"); 
        typeToName.put(Types.OTHER, "OTHER"); 
        typeToName.put(Types.REAL, "REAL"); 
        typeToName.put(Types.REF, "REF"); 
        typeToName.put(Types.SMALLINT, "SMALLINT"); 
        typeToName.put(Types.STRUCT, "STRUCT"); 
        typeToName.put(Types.TIME, "TIME"); 
        typeToName.put(Types.TIMESTAMP, "TIMESTAMP"); 
        typeToName.put(Types.TINYINT, "TINYINT"); 
        typeToName.put(Types.VARBINARY, "VARBINARY"); 
        typeToName.put(Types.VARCHAR, "VARCHAR"); 
        jdbcTypeValue2NameMap = Collections.unmodifiableMap(typeToName);
        
        
    	Map<Integer, String> javaTypeToName = new HashMap<Integer, String>(45);
        javaTypeToName.put(Types.ARRAY,  Object.class.getSimpleName());
        javaTypeToName.put(Types.BIGINT, Long.class.getSimpleName());
        javaTypeToName.put(Types.BINARY, "byte[]"); 
        javaTypeToName.put(Types.BIT, Boolean.class.getSimpleName());
        javaTypeToName.put(Types.BLOB,"byte[]"); 
        javaTypeToName.put(Types.BOOLEAN,  Boolean.class.getSimpleName());
        javaTypeToName.put(Types.CHAR,  String.class.getSimpleName());
        javaTypeToName.put(Types.CLOB,  String.class.getSimpleName());
        javaTypeToName.put(Types.DATALINK,  Object.class.getSimpleName());
        javaTypeToName.put(Types.DATE,  Date.class.getSimpleName());
        javaTypeToName.put(Types.DISTINCT,  Object.class.getSimpleName());
        javaTypeToName.put(Types.DOUBLE,  Double.class.getSimpleName());
        javaTypeToName.put(Types.FLOAT,  Double.class.getSimpleName());
        javaTypeToName.put(Types.INTEGER,  Integer.class.getSimpleName());
        javaTypeToName.put(Types.JAVA_OBJECT,  Object.class.getSimpleName());
        javaTypeToName.put(Jdbc4Types.LONGNVARCHAR,  String.class.getSimpleName());
        javaTypeToName.put(Types.LONGVARBINARY,  "byte[]"); 
        javaTypeToName.put(Types.LONGVARCHAR,String.class.getSimpleName());
        javaTypeToName.put(Jdbc4Types.NCHAR, String.class.getSimpleName());
        javaTypeToName.put(Jdbc4Types.NCLOB, String.class.getSimpleName());
        javaTypeToName.put(Jdbc4Types.NVARCHAR,String.class.getSimpleName());
        javaTypeToName.put(Types.NULL, Object.class.getSimpleName());
        javaTypeToName.put(Types.OTHER, Object.class.getSimpleName());
        javaTypeToName.put(Types.REAL, Float.class.getSimpleName());
        javaTypeToName.put(Types.REF, Object.class.getSimpleName());
        javaTypeToName.put(Types.SMALLINT, Short.class.getSimpleName());
        javaTypeToName.put(Types.STRUCT, Object.class.getSimpleName());
        javaTypeToName.put(Types.TIME, Date.class.getSimpleName());
        javaTypeToName.put(Types.TIMESTAMP,Date.class.getSimpleName());
        javaTypeToName.put(Types.TINYINT, Byte.class.getSimpleName());
        javaTypeToName.put(Types.VARBINARY,"byte[]"); 
        javaTypeToName.put(Types.VARCHAR,String.class.getSimpleName());
        jdbcTypeValue2JavaTypeNameMap= Collections.unmodifiableMap(javaTypeToName);

        Map<String, Integer> jdbcTypeName2ValueMapTemp = new HashMap<String, Integer>(45);
        jdbcTypeName2ValueMapTemp.put("ARRAY", Types.ARRAY); 
        jdbcTypeName2ValueMapTemp.put("BIGINT", Types.BIGINT); 
        jdbcTypeName2ValueMapTemp.put("BINARY", Types.BINARY); 
        jdbcTypeName2ValueMapTemp.put("BIT", Types.BIT); 
        jdbcTypeName2ValueMapTemp.put("BLOB", Types.BLOB); 
        jdbcTypeName2ValueMapTemp.put("BOOLEAN", Types.BOOLEAN); 
        jdbcTypeName2ValueMapTemp.put("CHAR", Types.CHAR); 
        jdbcTypeName2ValueMapTemp.put("CLOB", Types.CLOB); 
        jdbcTypeName2ValueMapTemp.put("DATALINK", Types.DATALINK); 
        jdbcTypeName2ValueMapTemp.put("DATE", Types.DATE); 
        jdbcTypeName2ValueMapTemp.put("DECIMAL", Types.DECIMAL); 
        jdbcTypeName2ValueMapTemp.put("DISTINCT", Types.DISTINCT); 
        jdbcTypeName2ValueMapTemp.put("DOUBLE", Types.DOUBLE); 
        jdbcTypeName2ValueMapTemp.put("FLOAT", Types.FLOAT); 
        jdbcTypeName2ValueMapTemp.put("INTEGER", Types.INTEGER); 
        jdbcTypeName2ValueMapTemp.put("JAVA_OBJECT", Types.JAVA_OBJECT); 
        jdbcTypeName2ValueMapTemp.put("LONGVARBINARY", Types.LONGVARBINARY); 
        jdbcTypeName2ValueMapTemp.put("LONGVARCHAR", Types.LONGVARCHAR); 
        jdbcTypeName2ValueMapTemp.put("NCHAR", Jdbc4Types.NCHAR); 
        jdbcTypeName2ValueMapTemp.put("NCLOB", Jdbc4Types.NCLOB); 
        jdbcTypeName2ValueMapTemp.put("NVARCHAR", Jdbc4Types.NVARCHAR); 
        jdbcTypeName2ValueMapTemp.put("LONGNVARCHAR", Jdbc4Types.LONGNVARCHAR); 
        jdbcTypeName2ValueMapTemp.put("NULL", Types.NULL); 
        jdbcTypeName2ValueMapTemp.put("NUMERIC", Types.NUMERIC); 
        jdbcTypeName2ValueMapTemp.put("OTHER", Types.OTHER); 
        jdbcTypeName2ValueMapTemp.put("REAL", Types.REAL); 
        jdbcTypeName2ValueMapTemp.put("REF", Types.REF); 
        jdbcTypeName2ValueMapTemp.put("SMALLINT", Types.SMALLINT); 
        jdbcTypeName2ValueMapTemp.put("STRUCT", Types.STRUCT); 
        jdbcTypeName2ValueMapTemp.put("TIME", Types.TIME); 
        jdbcTypeName2ValueMapTemp.put("TIMESTAMP", Types.TIMESTAMP); 
        jdbcTypeName2ValueMapTemp.put("TINYINT", Types.TINYINT); 
        jdbcTypeName2ValueMapTemp.put("VARBINARY", Types.VARBINARY); 
        jdbcTypeName2ValueMapTemp.put("VARCHAR", Types.VARCHAR); 
        jdbcTypeName2ValueMap = Collections.unmodifiableMap(jdbcTypeName2ValueMapTemp);
    }

    /**
     * Utility Class - no instances
     */
    private TypeNameTranslator() {
        super();
    }

    /**
     * Translates from a java.sql.Types values to the proper iBATIS string
     * representation of the type.
     * 
     * @param jdbcType
     *            a value from java.sql.Types
     * @return the iBATIS String representation of a JDBC type
     */
    public static String getJdbcTypeName(int jdbcType) {
        String answer = jdbcTypeValue2NameMap.get(jdbcType);
        if (answer == null) {
            answer = "OTHER"; 
        }

        return answer;
    }
    
    public static String getJavaTypeName(int jdbcType) {
        String answer = jdbcTypeValue2JavaTypeNameMap.get(jdbcType);
        if (answer == null) {
            answer = Object.class.getSimpleName(); 
        }
        return answer;
    }

    /**
     * Translates from the string representation of the type to the
     * java.sql.Types value.
     * 
     * @param jdbcTypeName
     *            the iBATIS String representation of a JDBC type
     * @return a value from java.sql.Types
     */
    public static int getJdbcType(String jdbcTypeName) {
        Integer answer = jdbcTypeName2ValueMap.get(jdbcTypeName);
        if (answer == null) {
            answer = Types.OTHER;
        }

        return answer;
    }
}
