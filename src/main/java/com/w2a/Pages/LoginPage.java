package com.w2a.Pages;
import java.io.IOException;
import com.w2a.TestBase.TopMenu;

public class LoginPage extends TopMenu
{
	public LoginPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public AppsPage login(String Username, String Password) throws IOException
	{
		type("username_XPATH", Username);
		System.out.println("username entered");
		click("clickNextBtn_XPATH");
		
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		type("password_XPATH" , Password);
		click("signInBtn_XPATH");
		
		return new AppsPage();
	}
		
	public void forgotPasswordLink()
	{
		
	}
	
	public void signIn_Google()
	{
		
	}
	
	public void signIn_Microsoft()
	{
		
	}
	
	public void signIn_LinkedIn()
	{
		
	}
	
	public void signIn_Facebook()
	{
		
	}

	public void signIn_Apple()
	{
		
	}
	
	public void oneAuthLink()
	{
		
	}
}
