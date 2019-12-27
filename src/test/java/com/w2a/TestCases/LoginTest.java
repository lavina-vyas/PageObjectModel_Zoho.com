package com.w2a.TestCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.w2a.Pages.HomePage;
import com.w2a.Pages.LoginPage;
import utilities.Utilities;

public class LoginTest extends BaseTest
{
	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void loginTest(Hashtable<String,String> data) throws IOException
	{
		HomePage page = new HomePage();
		LoginPage lp = page.loginLink();
		lp.login(data.get("Username"), data.get("Password"));
		System.out.println("This test is passed");
		//CreateNewAccount cna = ap.CRM_App();
		//cna.createNewAccount();
		
		//Assert.fail("Login test failed");
	}
}
