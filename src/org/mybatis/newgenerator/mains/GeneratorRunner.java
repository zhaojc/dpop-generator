package org.mybatis.newgenerator.mains;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.newgenerator.common.contents.Messages;
import org.mybatis.newgenerator.core.plugins.GeneratorPlugin;
import org.mybatis.newgenerator.core.plugins.impl.DpopCoderGeneratorPlugin;
import org.mybatis.newgenerator.utils.Logger;


/**
 * 
 * @author huhailiang
 * @date 2014-6-26 ����6:38:12
 * @calss org.mybatis.newgenerator.mainsGeneratorRunner.java
 * @todo TODO
 */
public class GeneratorRunner {

    private static final String CONFIG_FILE = "-configfile"; 
    private static final String OVERWRITE = "-overwrite"; 
    private static final String HELP_1 = "-?"; 
    private static final String HELP_2 = "-h"; 
    
	/**
	 * 
	 * 代码生成器入口
	 * 
	 * java -Djava.ext.dirs=%lib% -jar mybatis-generator-x.x.x.jar -configfile file_name -overwrite
	 * 
	 * java  -Djava.ext.dirs=%CD%\lib  -jar %CD%\bin\generator.jar  -configfile %CD%\conf\generator.xml -overwrite
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            usage();
            System.exit(0);
            return; 
        }
        Map<String, String> arguments = parseCommandLine(args);
        
        if (arguments.containsKey(HELP_1)) {
            usage();
            System.exit(0);
            return; // only to satisfy compiler, never returns
        }

        if (!arguments.containsKey(CONFIG_FILE)) {
            Logger.writeLine(Messages.getString("RuntimeError.0")); 
            return;
        }
        
        String configfile = arguments.get(CONFIG_FILE);
        File configurationFile = new File(configfile);
        if (!configurationFile.exists()) {
            Logger.writeLine(Messages.getString("RuntimeError.0", configfile)); 
            return;
        }
        
        GeneratorPlugin plugin = DpopCoderGeneratorPlugin.getInstance();
        plugin.initConfig(configurationFile,arguments.containsKey(OVERWRITE));
        plugin.startGenerator();
        
        
	}
	
    private static Map<String, String> parseCommandLine(String[] args) {
        List<String> errors = new ArrayList<String>();
        
        Map<String, String> arguments = new HashMap<String, String>();

        for (int i = 0; i < args.length; i++) {
            if (CONFIG_FILE.equalsIgnoreCase(args[i])) {
                if ((i + 1) < args.length) {
                    arguments.put(CONFIG_FILE, args[i + 1]);
                } else {
                    errors.add(Messages.getString("RuntimeError.3", CONFIG_FILE)); 
                }
                i++;
            } else if (OVERWRITE.equalsIgnoreCase(args[i])) {
                arguments.put(OVERWRITE, "Y"); 
            }else if (HELP_1.equalsIgnoreCase(args[i])) {
                arguments.put(HELP_1, "Y"); 
            } else if (HELP_2.equalsIgnoreCase(args[i])) {
                arguments.put(HELP_1, "Y"); 
            } else {
                errors.add(Messages.getString("RuntimeError.3", args[i])); 
            }
        }

        if (!errors.isEmpty()) {
            for (String error : errors) {
            	Logger.writeLine(error);
            }
            System.exit(-1);
        }

        return arguments;
    }

    /**
     * 如果输入参数不合法，则打印正确的使用方式
     * */
    private static void usage() {
        String lines = Messages.getString("Usage.Lines"); 
        int iLines = Integer.parseInt(lines);
        for (int i = 0; i < iLines; i++) {
            String key = "Usage." + i; 
            Logger.writeLine(Messages.getString(key));
        }
    }
}
