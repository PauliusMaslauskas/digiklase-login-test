package org.example.models;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;

public class DigiklaseLogin {

    public static WebDriver driver;
    public String email;
    public String password;
    public String username;


    public DigiklaseLogin(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public void fillAllFieldsLoginEmail(){
        fillEmail();
        fillPassword();
        clickLogin();
    }

    public void fillAllFieldsLoginUsername(){
        fillUsername();
        fillPassword();
        clickLogin();
    }

    public void fillEmail() {
        WebElement inputField = driver.findElement(By.id("email"));
        inputField.sendKeys(email);
    }

    public void fillPassword() {
        WebElement inputField = driver.findElement(By.id("password"));
        inputField.sendKeys(password);
    }
    public void fillUsername() {
        WebElement inputField = driver.findElement(By.id("username"));
        inputField.sendKeys(username);
    }

    public void clickLogin(){
        WebElement buttonSubmit = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/section/form/div[5]/button"));
        buttonSubmit.click();
    }


}
