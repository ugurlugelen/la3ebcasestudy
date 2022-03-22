package com.la3eb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailPage extends BasePage{

    @FindBy(css = "div.Product_info__1eaRV h1")
    public WebElement productName;

    @FindBy(css = "button.btn.Product_addToButton__3IXtt" )
    public WebElement pdpAddCartButton;

    @FindBy(className = "MiniCartItem_name__1baVF")
    public WebElement productNameAtMiniCart;


}
