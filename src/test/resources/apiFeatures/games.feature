Feature: Games Path Get Responses

  Scenario: Get_List_Of_Games
    Given user sends get requests to "games" endpoint
    Then user verifies response has correct output

  @getgames
  Scenario Outline: Get_List_of_DLC's_For_The_Game
    Given user sends get requests to "games" "<games_pk>" "additions" endpoint
    Then user verifies slug name contains "<games_pk>"

    Examples:
      | games_pk                   |
      | grand-theft-auto-v         |
      | borderlands-2              |
      | portal-2                   |
      | tomb-raider                |
      | the-elder-scrolls-v-skyrim |