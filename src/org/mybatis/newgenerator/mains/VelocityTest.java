package org.mybatis.newgenerator.mains;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(Velocity.RESOURCE_LOADER, "class");
		engine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		try {
			// 进行初始化操作
			engine.init();
			// 加载模板，设定模板编码
			Template t = engine.getTemplate("org/mybatis/newgenerator/template/myBatis3/bo.vm", "UTF-8");
			// 设置初始化数据
			VelocityContext context = new VelocityContext();
			context.put("name", "张三");
			
			String[] hobbyArray={"吃饭","喝水","洗澡"};
			context.put("hobby", "爱好");
			context.put("hobbyArray", hobbyArray);
			
			// 设置输出
			StringWriter writer = new StringWriter();
			// 将环境数据转化输出
			t.merge(context, writer);
			System.out.println(writer.toString());;
		} catch (Exception e) {
			throw new RuntimeException("模版转化错误!");
		}
	}

}
