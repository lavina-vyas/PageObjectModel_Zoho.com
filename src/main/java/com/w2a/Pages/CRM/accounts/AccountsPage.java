package com.w2a.Pages.CRM.accounts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.w2a.Pages.CRM.CreateNewAccount;
import com.w2a.TestBase.Base;

public class AccountsPage extends Base {
	
	public AccountsPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateNewAccount clickAddIcon() throws IOException
	{
		click("AccountsLink_CSS");
		
		System.out.println("Accounts link clicked");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		click("Create_New_Account_Btn_CSS");
		
		return new CreateNewAccount();
	}

}
