package com.w2a.Pages;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.w2a.TestBase.TopMenu;


public class HomePage extends TopMenu
{
	public HomePage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void customersLink()
	{
		driver.findElement(By.cssSelector(".zh-customers")).click();
	}
	
	public void supportLink()
	{
		driver.findElement(By.cssSelector(".zh-support")).click();
	}
	
	public void contactSalesLink()
	{
		driver.findElement(By.cssSelector(".zh-contact")).click();
	}
	
	public LoginPage loginLink() throws IOException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertTrue(isElementPresent(By.cssSelector(".zh-login")));
		click("LoginLink_CSS");	
		
		return new LoginPage();
	}
	
	public void freeSignUpLink()
	{
		driver.findElement(By.cssSelector(".zh-signup")).click();
	}
	
	public void selectLangsuageLink()
	{
		driver.findElement(By.cssSelector(".zgh-localSelect")).click();
	}
	
	public void selectSearchButton()
	{
		driver.findElement(By.cssSelector(".menu-search-icon")).click();
	}
	
	public void validateFooterLinks()
	{
		driver.findElement(By.cssSelector(".footer-features > a:nth-child(2)")).click();
	}
}
