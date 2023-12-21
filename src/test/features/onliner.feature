Feature: Onliner Main Page

  Scenario: User open "Auto" page
    Given main page is opened
    When user move mouse to the "Автобарахолка" menu item
    Then sub menu "Автобарахолка" is opened
    And section "Отзывы об авто" is shown as child of "Автобарахолка" sub menu
    And section "Новые авто" is shown as child of "Автобарахолка" sub menu
    And section "С пробегом" is shown as child of "Автобарахолка" sub menu

  Scenario: User see "Rental" dropdown
    Given main page is opened
    When user move mouse to the "Дома и квартиры" menu item
    Then sub menu "Продажа" is opened
    And dependent sections of "Продажа" menu are shown:
      | Минск   |
      | Брест   |
      | Гомель  |
      | Витебск |
      | Гродно  |
      | Могилев |
    And dependent sections of "Продажа" menu are shown:
      | 1-комнатные  |
      | 2-комнатные  |
      | 3-комнатные  |
      | 4+-комнатные |
    And dependent sections of "Продажа" menu are shown:
      | До 30 000 $     |
      | 30 000–80 000 $ |
      | От 80 000 $     |
