package com.xuecheng.test.freemarker;

import com.xuecheng.test.freemarker.model.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerTest {

	//测试静态化，基于ftl模板文件生成html文件
	@Test
	public void testGenerateHtml() throws IOException, TemplateException {
		//定义配置类
		Configuration configuration = new Configuration(Configuration.getVersion());
		//定义模板
		//得到classpath的路径
		String classpath = this.getClass().getResource("/").getPath();
		//定义模板路径
		configuration.setDirectoryForTemplateLoading(new File(classpath+"/templates/"));
		//获取模板文件内容
		Template template = configuration.getTemplate("test1.ftl");
		//定义数据模型
		Map map = getMap();
		//静态化
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		//System.out.println(content);
		InputStream inputStream = IOUtils.toInputStream(content);
		FileOutputStream outputStream = new FileOutputStream(new File("d:/test1.html"));
		//输出文件
		IOUtils.copy(inputStream,outputStream);
		inputStream.close();
		outputStream.close();
	}

	//基于模板文件的内容（字符串）生成html文件
	@Test
	public void testGenerateHtmlByString() throws IOException, TemplateException {
		//定义配置类
		Configuration configuration = new Configuration(Configuration.getVersion());

		//定义模板
		//模板内容（字符串）
		//模板内容，这里测试时使用简单的字符串作为模板
		String templateString="" +
				"<html>\n" +
				" <head></head>\n" +
				" <body>\n" +
				" 名称：${name}\n" +
				" </body>\n" +
				"</html>";
		//使用模板加载器变为模板
		StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
		stringTemplateLoader.putTemplate("template",templateString);
		//在配置中设置模板加载器
		configuration.setTemplateLoader(stringTemplateLoader);
		//获取模板内容
		Template template = configuration.getTemplate("template", "utf-8");
		//定义数据模型
		Map map = getMap();
		//静态化
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
		//System.out.println(content);
		InputStream inputStream = IOUtils.toInputStream(content);
		FileOutputStream outputStream = new FileOutputStream(new File("d:/test1.html"));
		//输出文件
		IOUtils.copy(inputStream,outputStream);
		inputStream.close();
		outputStream.close();

		//定义数据模型
		//静态化
	}

	//获取数据模型
	public Map getMap() {
		Map map = new HashMap();
		//map就是freemarker模板所使用的数据
		map.put("name", "topband");

		Student stu1 = new Student();
		stu1.setName("小明");
		stu1.setAge(18);
		stu1.setMoney(1000.86f);
		stu1.setBirthday(new Date());
		Student stu2 = new Student();
		stu2.setName("小红");
		stu2.setMoney(200.1f);
		stu2.setAge(19);
		stu2.setBirthday(new Date());
		//朋友列表
		List<Student> friends = new ArrayList<>();
		friends.add(stu1);
		//给第二名学生设置朋友列表
		stu2.setFriends(friends);
		//给第二名学生设置最好朋友
		stu2.setBestFriend(stu1);
		List<Student> stus = new ArrayList<>();
		stus.add(stu1);
		stus.add(stu2);
		//将学生列表放在数据模型中
		map.put("stus", stus);
		//准备map数据
		HashMap<String, Student> stuMap = new HashMap<>();
		//stuMap.put("stu1", stu1);
		stuMap.put("stu2", stu2);
		//向数据模型放数据
		map.put("stu1", stu1);
		//向数据模型放map数据
		map.put("stuMap", stuMap);

		map.put("point", 102920122);

		return map;
	}
}
