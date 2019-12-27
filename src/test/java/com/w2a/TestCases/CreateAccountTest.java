package com.w2a.TestCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.w2a.Pages.AppsPage;
import com.w2a.Pages.CRM.CreateNewAccount;
import com.w2a.Pages.CRM.accounts.AccountsPage;
import com.w2a.TestBase.Base;

import utilities.Utilities;

public class CreateAccountTest{

	public CreateAccountTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void createAccountTest(Hashtable<String,String> data) throws IOException 
	{
		AppsPage ap = new AppsPage();
		ap.CRM_App();
		AccountsPage acc = Base.menu.go_to_Accounts();
		CreateNewAccount cna = acc.clickAddIcon();
		cna.createNewAccount(data.get("AccountName"));
		System.out.println("Test case 2 is passed");
		//Assert.fail("Login test failed");
	}
}
