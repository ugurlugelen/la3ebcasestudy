package com.la3eb.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ReusableMethods;

import java.util.List;
import java.util.Locale;

public class RegistrationPage extends BasePage{

    @FindBy(xpath = "//*[text()='Register']")
    public WebElement registerTab;

    @FindBy(css = "button.btn.btn-primary.btn-xlg.btn-fill")
    public WebElement createAccountButton;

    @FindBy(css = "div.GlobalMessage_messageIcon__27sT9")
    public WebElement existedAccountToastMessage;

    @FindBy(css = "p.errorMessage")
    public List<WebElement> fieldValidationErrorMessages;

    public void completeRegistration(String email, String userName, String password){
        registerTab.click();
        String dataChord = Keys.chord(email,Keys.TAB,userName,Keys.TAB,password);
        registrationPageElements("email").
                sendKeys(dataChord);
        ReusableMethods.clickElementByJS(registrationPageElements("legal"));
        int i = 0;
        while(i<=2) {
            if (!createAccountButton.isEnabled()) {
                registrationPageElements("password").clear();
                registrationPageElements("password").sendKeys(password);
            }
            i++;
        }
        createAccountButton.click();
    }

    public String validationErrorMessage(String field){
        List<WebElement> list = fieldValidationErrorMessages;
        String errorMessage = null;
        for (WebElement w: list) {
            if(w.getText().toUpperCase().contains(field.toUpperCase())){
                errorMessage = w.getText();
                break;
            }
        }
        return errorMessage;
    }


}
