package com.la3eb.stepDefinitions;

import com.la3eb.pages.AllPages;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UIStepDefs {

    AllPages allPages = new AllPages();
    Actions actions;
    String expectedEmail;
    String expectedProductName;

    @Given("user goes to {string} landing page")
    public void user_goes_to_landing_page(String environment) {
        if(environment.equalsIgnoreCase("production")){
            Driver.getDriver().get(ConfigReader.getProperty("productionURL"));
        }else if(environment.equalsIgnoreCase("staging")){
            Driver.getDriver().get(ConfigReader.getProperty("stagingURL"));
        }else{
            Driver.getDriver().get(ConfigReader.getProperty("productionURL"));
        }
        allPages.homePage().acceptCookies.click();
    }
    @Given("user completes registration with using {string} {string} and {string}")
    public void user_completes_registration_with_using_and(String email, String userName, String password) {
        List<String> registerData = ReusableMethods.registerData();
        expectedEmail = registerData.get(0);
        ReusableMethods.changeConfigPropDesiredValue("registeredEmail",registerData.get(0));
        ReusableMethods.changeConfigPropDesiredValue("registeredUserName",registerData.get(1));
        allPages.homePage().loginPageButton.click();
        allPages.registrationPage().completeRegistration(registerData.get(0),registerData.get(1),registerData.get(2));
    }

    @Given("user completes registration with using {string}")
    public void user_completes_registration_with_using(String configKey) {
        List<String> registerData = ReusableMethods.registerData();
        allPages.homePage().loginPageButton.click();
        if(configKey.equals("registeredEmail")){
            allPages.registrationPage().completeRegistration(ConfigReader.getProperty(configKey),registerData.get(1),registerData.get(2));
        }else if(configKey.equals("registeredUserName")){
            allPages.registrationPage().completeRegistration(registerData.get(0),ConfigReader.getProperty(configKey),registerData.get(2));
        }
    }

    @Then("user verifies registration is succeeded with the same {string}")
    public void user_verifies_registration_is_succeeded_with_the_same(String email) {
        ReusableMethods.waitForVisibility(allPages.homePage().profileMenuButton,40);
        allPages.homePage().profileMenuButton.click();
        allPages.homePage().selectProfileMenu("Edit profile");
        allPages.profilePage().selectProfilePageSubMenu("Account details");
        String actualEmail = ReusableMethods.getElementTextByJS(allPages.profilePage().emailReadOnlyValue);
        Assert.assertEquals("Emails does not match",
                expectedEmail,actualEmail);
    }

    @Then("user verifies {string} message is displayed")
    public void userVerifiesMessageIsDisplayed(String message) {
        ReusableMethods.waitForVisibility(allPages.registrationPage().existedAccountToastMessage,10);
        String actualText = ReusableMethods.getElementTextByJS(allPages.registrationPage().existedAccountToastMessage);
        System.out.println(actualText);
        //Assert.assertEquals("User can register a new account with an existed email",ConfigReader.getProperty(message),actualText);
        Assert.assertTrue(allPages.registrationPage().existedAccountToastMessage.isDisplayed());
    }

    @Then("user verifies {string} validation message appears for {string} field")
    public void user_verifies_validation_message_appears_for_field(String message, String field) {

        if(message.equalsIgnoreCase("existedUserNameError") || message.equalsIgnoreCase("existedEmailError")){
            Assert.assertEquals("User can register a new account with an existed "+field+"",
                    ConfigReader.getProperty(message),allPages.registrationPage().validationErrorMessage(field));
        }else {
            Assert.assertEquals("Validation message is missing or wrong for "+field+" textbox",
                    message,allPages.registrationPage().validationErrorMessage(field));
        }
    }

    @Given("user navigates to {string} page")
    public void user_navigates_to_page(String pageTextName) {
        allPages.homePage().loginPageButton.click();
        allPages.homePage().findElementWithTextName(pageTextName).click();
    }
    @Given("user types {string} to {string} box")
    public void user_types_to_box(String fieldDataText, String fieldName) {
        allPages.homePage().registrationPageElements(fieldName).click();
        allPages.homePage().registrationPageElements(fieldName).sendKeys(fieldDataText);
    }
    @When("user clears {string} box")
    public void user_clears_box(String fieldName) {
        allPages.homePage().registrationPageElements(fieldName).sendKeys(Keys.BACK_SPACE);
    }

    @Given("user moves mouse to {string} menu")
    public void user_moves_mouse_to_menu(String shopMenu) {
        actions = new Actions(Driver.getDriver());
        actions.moveToElement(allPages.homePage().headerMenuButtons(shopMenu)).build().perform();

    }
    @Given("user selects {string} and {string}")
    public void user_selects_and(String subMenu, String category) {
        actions = new Actions(Driver.getDriver());
        actions.moveToElement(allPages.homePage().desiredSubMenu(subMenu)).build().perform();
        actions.moveToElement(allPages.homePage().desiredShoppingCategory(category)).click().build().perform();
    }

    @Given("user selects {string} filter with {string} option")
    public void user_selects_filter_with_option(String filterName, String subOptionName) {
        allPages.productListingPage().selectDesiredFilter(filterName);
        allPages.productListingPage().selectFilterSubOption(subOptionName);
        ReusableMethods.waitFor(3);
    }

    @When("user adds first item to cart")
    public void user_adds_first_item_to_cart() {
        List<WebElement> productContainers = allPages.productListingPage().productContainers;
        allPages.productListingPage().productContainers.get(0).click();
        expectedProductName = allPages.productDetailPage().productName.getText();
        allPages.productDetailPage().pdpAddCartButton.click();
    }
    @Then("user verifies item succesfully added to cart")
    public void user_verifies_item_succesfully_added_to_cart() {
        Assert.assertEquals("Wrong product is added to cart",expectedProductName,
                            allPages.productDetailPage().productNameAtMiniCart.getText());
    }

    @Given("user logs in with {string} and {string}")
    public void user_logs_in_with_and(String email, String password) {
        allPages.homePage().loginWithValidAccount(ConfigReader.getProperty(email),ConfigReader.getProperty(password));
        ReusableMethods.waitForVisibility(allPages.homePage().profileMenuButton,40);
    }

    @Given("user clicks on {string} button")
    public void user_clicks_on_button(String socialMediaName) {
        allPages.homePage().socialLoginButton(socialMediaName).click();
    }

    @When("user completes login with {string} using {string} and {string}")
    public void user_completes_login_with_facebook_using_and(String desiredWindow, String email, String password) {

        String parent = Driver.getDriver().getWindowHandle();
        Set<String>s = Driver.getDriver().getWindowHandles();
        Iterator<String> I1= s.iterator();
        while(I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                Driver.getDriver().switchTo().window(child_window);
            }
            allPages.homePage().loginWithFacebook(ConfigReader.getProperty(email), ConfigReader.getProperty(password));
            ReusableMethods.waitFor(5000);
            Driver.getDriver().switchTo().window(parent);

        }
    }
}
