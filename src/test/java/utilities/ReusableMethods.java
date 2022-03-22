package utilities;

import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ReusableMethods {

    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void clickElementByJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("arguments[0].click();", element);
    }

    public static String getElementTextByJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        return (String) jsexecutor.executeScript("return arguments[0].value", element);
    }

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public static void switchToNewTab(int tabIndex){
        ArrayList<String> windows = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windows.get(tabIndex));
    }

    public static List<String> registerData(){
        Faker faker = new Faker();
        Random random = new Random();
        List<String> registrationData = new ArrayList<>();
        registrationData.add(0,faker.internet().emailAddress());
        registrationData.add(1,faker.address().firstName().concat(String.valueOf(random.nextInt(500))));
        registrationData.add(2, String.valueOf(generatePassword(9)));
        return registrationData;
    }

    public static void changeConfigPropDesiredValue(String configValue, String newValue){

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("configuration.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty(configValue,newValue);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("configuration.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.store(fileOutputStream,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 3; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }


}
