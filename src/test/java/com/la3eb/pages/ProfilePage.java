package com.la3eb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy(css = ".UserLayout_nav__16waH a")
    public List<WebElement> profilePageSubMenus;

    @FindBy(xpath = "(//section[@class='my-feed-search-input group entered']//input)[1]")
    public WebElement emailReadOnlyValue;

    public void selectProfilePageSubMenu(String desiredSubMenu){
        List<WebElement> options = profilePageSubMenus;

        for (WebElement w: options) {
            if (w.getText().equalsIgnoreCase(desiredSubMenu)){
                w.click();
                break;
            }
        }
    }


}
