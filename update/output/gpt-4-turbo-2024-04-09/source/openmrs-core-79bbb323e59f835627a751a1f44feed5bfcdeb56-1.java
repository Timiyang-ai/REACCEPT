@Test
public void parsePersonName_shouldHandleNamesWithMultipleSpacesAndEndingWithWhitespace() throws Exception {
    // Given a name with multiple spaces and ending with whitespace
    String inputName = "John Adam Smith ";

    // When parsing the person name
    PersonName parsedName = Context.getPersonService().parsePersonName(inputName.trim());

    // Then the parsed name should correctly split into first, middle, and last names
    assertEquals("John", parsedName.getGivenName());
    assertEquals("Adam", parsedName.getMiddleName());
    assertEquals("Smith", parsedName.getFamilyName());
    assertNull("FamilyName2 should be null for names with three parts", parsedName.getFamilyName2());

    // Additionally, test with a name that has an extra space and ends with whitespace
    inputName = "John Adam Smith  ";
    parsedName = Context.getPersonService().parsePersonName(inputName.trim());

    // Then the parsed name should still correctly split into first, middle, and last names
    assertEquals("John", parsedName.getGivenName());
    assertEquals("Adam", parsedName.getMiddleName());
    assertEquals("Smith", parsedName.getFamilyName());
    assertNull("FamilyName2 should be null for names with three parts", parsedName.getFamilyName2());
}