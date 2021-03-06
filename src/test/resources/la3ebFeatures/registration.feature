Feature: Case_Study

  Background:Pre_Condition_User_Leads_to_Home_Page
    Given user goes to "production" landing page

  Scenario: TC_001_Happy_Path_Register_Flow
    And user completes registration with using "email" "userName" and "password"
    Then user verifies registration is succeeded with the same "email"


  Scenario: TC_002_Register_with_Existed_Email
    And user completes registration with using "registeredEmail"
    Then user verifies "existedEmailError" message is displayed

  Scenario: TC_003_Register_with_Existed_UserName
    And user completes registration with using "registeredUserName"
    Then user verifies "existedUserNameError" validation message appears for "username" field

  Scenario Outline: TC_004_Registration_Missing_Field_Validations
    And user navigates to "Register" page
    And user types "<fieldData>" to "<registerPageField>" box
    When user clears "<registerPageField>" box
    Then user verifies "<missingFieldErrorMessage>" validation message appears for "<registerPageField>" field

    Examples:
      | fieldData | registerPageField | missingFieldErrorMessage |
      | ademd     | email             | Email is missing         |
      | b         | username          | Username is required     |
      | c         | password          | Password is missing      |

  Scenario Outline: TC_005_Registration_WrongData_Field_Validations
    And user navigates to "Register" page
    When user types "<fieldData>" to "<registerPageField>" box
    Then user verifies "<missingFieldErrorMessage>" validation message appears for "<registerPageField>" field

    Examples:
      | fieldData | registerPageField | missingFieldErrorMessage                                                                                                  |
      | abcd      | email             | Invalid email address                                                                                                     |
      | brt       | username          | Username must contain at least 4 characters.                                                                              |
      | ctyr      | password          | Password must contain at least 1 uppercase letter, 1 lowercase letter and 1 number and must be at least 8 characters long |


  Scenario: TC_006_Guest_User_Adds_Item_to_Cart
    And user moves mouse to "Shop" menu
    And user selects "PC & PC Accessories" and "Gaming Monitors"
    And user selects "Color" filter with "Black" option
    When user adds first item to cart
    Then user verifies item succesfully added to cart


  Scenario:TC_007_Logged_In_User_Adds_Item_to_Cart
    And user logs in with "testEmail" and "testPassword"
    And user moves mouse to "Shop" menu
    And user selects "PC & PC Accessories" and "Gaming Monitors"
    And user selects "Color" filter with "Black" option
    When user adds first item to cart
    Then user verifies item succesfully added to cart

  Scenario: TC_008_Login_with_Valid_Facebook_Credentials
    And user navigates to "Login" page
    And user clicks on "Facebook" button
    Then user completes login with "Facebook" using "facebookEmail" and "facebookPassword"








