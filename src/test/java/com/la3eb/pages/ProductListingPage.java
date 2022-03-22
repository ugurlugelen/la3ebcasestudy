package com.la3eb.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.ReusableMethods;

import java.util.List;

public class ProductListingPage extends BasePage{

    @FindBy(css = ".btn.btn-primary.ProductCard_orderButton__2vzTL")
    public List<WebElement> addCartbutton;

    @FindBy(className = "ProductCard_cardLabelContainer__VIB5W")
    public List<WebElement> productContainers;

    @FindBy(className = "ProductCard_name__2lvqP")
    public List<WebElement> productNames;

    @FindBy(className = "Accordion_accordion__3CL2K")
    public List<WebElement> filterOptions;

    @FindBy(className = "Facets_facetItem__3gOqv")
    public List<WebElement> filterSubOptions;


    public void selectDesiredFilter(String filterName){
        for (WebElement w : filterOptions) {
            if(w.getText().equals(filterName)){
                w.click();
                break;
            }
        }
    }

    public void selectFilterSubOption(String subOptionName){
        for (WebElement w : filterSubOptions) {
            if(w.getText().contains(subOptionName)){
                w.click();
                break;
            }
        }
    }


}
