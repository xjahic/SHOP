package com.learn2code.Shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTests {


	@Test
	public void test() {
		int a = 2;
		int b = 3;
		Assert.assertEquals(5, a+b);
	}
}

