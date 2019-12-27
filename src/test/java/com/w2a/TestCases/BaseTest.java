package com.w2a.TestCases;

import org.testng.annotations.AfterSuite;

import com.w2a.TestBase.Base;

public class BaseTest 
{
	@AfterSuite
	public void tearDown()
	{
		Base.quit();
	}
}
