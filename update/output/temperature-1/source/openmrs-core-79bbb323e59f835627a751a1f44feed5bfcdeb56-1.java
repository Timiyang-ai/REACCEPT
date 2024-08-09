@Test
public void parsePersonName_ShouldHandleFourPartNamesCorrectly() throws Exception {
    // Assuming there's a method to get person service and it's accessible via Context.
    // Test for a four-part name
    PersonName pname = Context.getPersonService().parsePersonName("John Adam Smith Doe");
    assertEquals("John", pname.getGivenName(), "Given name should be 'John'");
    assertEquals("Adam", pname.getMiddleName(), "Middle name should be 'Adam'");
    assertEquals("Smith", pname.getFamilyName(), "Family name should be 'Smith'");
    assertEquals("Doe", pname.getFamilyName2(), "Second family name should be 'Doe'");

    // Test to ensure whitespace is trimmed and does not affect parsing
    PersonName pnameWithWhitespace = Context.getPersonService().parsePersonName(" John Adam Smith Doe ");
    assertEquals("John", pnameWithWhitespace.getGivenName(), "Given name should be 'John'");
    assertEquals("Adam", pnameWithWhitespace.getMiddleName(), "Middle name should be 'Adam'");
    assertEquals("Smith", pnameWithWhitespace.getFamilyName(), "Family name should be 'Smith'");
    assertEquals("Doe", pnameWithWhitespace.getFamilyName2(), "Second family name should be 'Doe'");
    
    // Ensure the changes handling names with commas and spaces are covered
    PersonName pnameWithCommaSpace = Context.getPersonService().parsePersonName("Doe, John Adam");
    assertEquals("Doe", pnameWithCommaSpace.getFamilyName(), "Family name should be 'Doe'");
    assertEquals("John", pnameWithCommaSpace.getGivenName(), "Given name should be 'John'");
    assertEquals("Adam", pnameWithCommaSpace.getMiddleName(), "Middle name should be 'Adam'");
    
    // Handle names with trailing commas correctly
    PersonName pnameWithTrailingComma = Context.getPersonService().parsePersonName("Doe,");
    assertEquals("Doe", pnameWithTrailingComma.getGivenName(), "Given name should be 'Doe'");
    assertTrue(pnameWithTrailingComma.getMiddleName().isEmpty(), "Middle name should be empty");
    assertTrue(pnameWithTrailingComma.getFamilyName().isEmpty(), "Family name should be empty");
}