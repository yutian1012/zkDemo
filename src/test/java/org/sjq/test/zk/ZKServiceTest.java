package org.sjq.test.zk;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ZKServiceTest {
	@Resource
	private ZKService zkService;

	@Test
	public void testZK() throws Exception {
		String node="/zktest";
		
		if(!zkService.isNodeExists(node)) {
			zkService.createNode(node, "testnode");
		}
		
		String data=zkService.getNodeDate(node);
		
		assertTrue("testnode".equals(data));
	}

}
