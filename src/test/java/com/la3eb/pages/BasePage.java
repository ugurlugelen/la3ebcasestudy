package com.la3eb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public abstract class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    public WebElement registrationPageElements(String byName){
        return Driver.getDriver().findElement(By.xpath("//*[@name='"+byName+"']"));
    }

    public WebElement findElementWithTextName(String byTextName){
        return Driver.getDriver().findElement(By.xpath("//a[text()='"+byTextName+"']"));
    }

    public WebElement headerMenuButtons(String byTextName){
        return Driver.getDriver().findElement(By.xpath("//span[text()='"+byTextName+"']"));
    }

    public void loginWithFacebook(String email, String password){
        Driver.getDriver().findElement(By.id("email")).sendKeys(email,Keys.TAB,password,Keys.TAB,Keys.ENTER);
    }
}
