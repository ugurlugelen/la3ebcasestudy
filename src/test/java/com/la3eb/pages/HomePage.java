package com.la3eb.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Driver;

import java.util.List;

public class HomePage extends BasePage{

    @FindBy(className = "AllowCookiesDisclaimer_btn__37QQ1")
    public WebElement acceptCookies;

    @FindBy(className = "please-signin-link")
    public WebElement loginPageButton;

    @FindBy(className = "user-profile-link")
    public WebElement profileLinkButton;

    @FindBy(id = "profileMenuIcon")
    public WebElement profileMenuButton;

    @FindBy(className = "UserNav_item__2b2Pc")
    public List<WebElement> profileMenuOptions;

    @FindBy(css = ".MarketDropdown_leftContent__zlyz8 a")
    public List<WebElement> shoppingSubMenu;

    @FindBy(css = ".MarketDropdown_linksWrapper__21UsK.MarketDropdown_visible__1IYx9 a")
    public List<WebElement> shoppingSubCategory;

    @FindBy(css = ".btn.btn-primary.btn-xlg.btn-fill.LoginWrapper_modalButton__36IRG")
    public WebElement loginSubmitButton;

    public void loginWithValidAccount(String email, String password){
        loginPageButton.click();
        registrationPageElements("username").sendKeys(email, Keys.TAB,password);
        loginSubmitButton.click();

    }

    public WebElement socialLoginButton(String socialMediaName){
        return Driver.getDriver().findElement(By.xpath("//button[contains(@class,'"+socialMediaName+"')]"));
    }

    public WebElement desiredSubMenu(String subMenuName){
        WebElement element = null;
        for (WebElement w : shoppingSubMenu) {
            if(w.getText().equals(subMenuName)){
                element = w;
                break;
            }
        }
        return element;
    }

    public WebElement desiredShoppingCategory(String categoryName){
        WebElement element = null;
        for (WebElement w : shoppingSubCategory) {
            if(w.getText().equals(categoryName)){
                element = w;
                break;
            }
        }
        return element;
    }


    public void selectProfileMenu(String desiredMenu){
        List<WebElement> options = profileMenuOptions;
        options.stream().
                filter(x-> x.getText().equalsIgnoreCase(desiredMenu)).
                forEach(WebElement::click);
    }
}
