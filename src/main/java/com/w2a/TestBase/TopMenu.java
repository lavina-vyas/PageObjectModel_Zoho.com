package com.w2a.TestBase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.Pages.CRM.accounts.AccountsPage;

public class TopMenu extends Base{

	public TopMenu() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void go_to_Home() {

	}

	public void go_to_Leads() {

	}

	public void go_to_Contacts() {

	}

	public AccountsPage go_to_Accounts() throws IOException {
		
		click("AccountsLink_CSS");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Clicking + icon to create a new account
		return new AccountsPage();
	}

	public void go_to_Deals() {

	}

	public void go_to_Activities() {

	}

	public void go_to_Reports() {

	}

	public void go_to_Projects() {

	}

	public void signOut() {

	}
}
