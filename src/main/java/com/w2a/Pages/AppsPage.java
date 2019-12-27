package com.w2a.Pages;

import java.io.IOException;

import org.openqa.selenium.By;

import com.w2a.Pages.CRM.CreateNewAccount;
import com.w2a.Pages.CRM.accounts.AccountsPage;
import com.w2a.TestBase.TopMenu;

public class AppsPage extends TopMenu
{		
		public AppsPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

		public AccountsPage CRM_App() throws IOException
		{
			click("Select_CRM_APP_XPATH");
			System.out.println("Entered CRM block");
			
			return new AccountsPage();
		}
}
