Feature: Pets APIs

  @get_pets
  Scenario: Get all the PETS
    When I want to know all the pets in the clinic
    Then I should receive 13 pets


  @add_new_pet
  Scenario: Add a new pet with required information
    Given the resource of the API is "api/"
    When I want to know all the pets in the clinic
    Then the response code should be "200"
    And I have the details of an owner
    And I have the details of a pet category
    When I add a new pet
    Then the response code should be "201"
    And the pet is added successfully


  @create_vet
  Scenario Outline: Create a vet for a specific specialty
    Given the resource of the API is "api/"
    When I want to know all the specialties
    Then the response code should be "200"
    When I create a vet with specialty "<specialty_id>"
    Then the response code should be "201"