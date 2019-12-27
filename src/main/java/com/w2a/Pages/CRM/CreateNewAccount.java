package com.w2a.Pages.CRM;
import java.io.IOException;

import org.openqa.selenium.By;
import com.w2a.TestBase.TopMenu;

public class CreateNewAccount extends TopMenu
{
	public CreateNewAccount() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createNewAccount(String AccountName)
	{
		type("AccountName_CSS", AccountName);
	}
}
