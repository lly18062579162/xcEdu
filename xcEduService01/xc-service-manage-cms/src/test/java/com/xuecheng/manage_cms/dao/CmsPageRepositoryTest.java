package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {

	@Autowired
	CmsPageRepository cmsPageRepository;

	@Test
	public void testFindAll() {
		List<CmsPage> all = cmsPageRepository.findAll();
		System.out.println(all);
	}

	//分页查询
	@Test
	public void testFindPage() {
		//分页参数
		int page = 1;//从0开始
		int size = 10;
		Pageable pageable = PageRequest.of(page, size);
		Page<CmsPage> all = cmsPageRepository.findAll(pageable);
		System.out.println(all);
	}

	@Test
	public void testFindAllByExample() {
		//分页参数
		int page = 0;//从0开始
		int size = 10;
		Pageable pageable = PageRequest.of(page, size);

		//条件值对象
		CmsPage cmsPage = new CmsPage();
		//要查询5a751fab6abb5044e0d19ea1站点的页面
		//cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
		//设置模板id条件
		//cmsPage.setTemplateId("5a925be7b00ffc4b3c1578b5");
		//设置页面别名
		cmsPage.setPageAliase("轮播");
		//条件匹配器
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
		//定义Example
		Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
		Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
		List<CmsPage> content = all.getContent();
		System.out.println(content);
	}

	//修改
	@Test
	public void testUpdate() {
		//查询对象
		Optional<CmsPage> optional = cmsPageRepository.findById("5abefd525b05aa293098fca6");
		if (optional.isPresent()){
			CmsPage cmsPage = optional.get();
			//设置要修改的值
			cmsPage.setPageName("test01");
			//...
			//修改
			CmsPage save = cmsPageRepository.save(cmsPage);
			System.out.println(save);
		}
	}

	//根据页面名称查询
	@Test
	public void testfindByPageName(){
		CmsPage cmsPage = cmsPageRepository.findByPageName("test01");
		System.out.println(cmsPage);
	}

}
