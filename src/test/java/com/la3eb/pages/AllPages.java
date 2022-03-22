package com.la3eb.pages;

public class AllPages {
    public AllPages(){

    }
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private ProfilePage profilePage;
    private ProductListingPage productListingPage;
    private ProductDetailPage productDetailPage;

    public HomePage homePage(){
        if(homePage == null){
            homePage = new HomePage();
        }
        return homePage;
    }

    public ProfilePage profilePage(){
        if(profilePage == null){
            profilePage = new ProfilePage();
        }
        return profilePage;
    }

    public RegistrationPage registrationPage(){
        if(registrationPage == null){
            registrationPage = new RegistrationPage();
        }
        return registrationPage;
    }

    public ProductListingPage productListingPage(){
        if(productListingPage == null){
            productListingPage = new ProductListingPage();
        }
        return productListingPage;
    }

    public ProductDetailPage productDetailPage(){
        if(productDetailPage == null){
            productDetailPage = new ProductDetailPage();
        }
        return productDetailPage;
    }

}
