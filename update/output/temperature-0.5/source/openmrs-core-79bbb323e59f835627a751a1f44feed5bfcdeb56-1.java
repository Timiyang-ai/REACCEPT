@Test
public void parsePersonName_ShouldAccuratelyAssignNameParts() throws Exception {
    // Test case for a name with four parts, ensuring each part is correctly assigned
    PersonName personName = Context.getPersonService().parsePersonName("John Adam Smith Doe");
    assertEquals("Expected given name to be 'John'", "John", personName.getGivenName());
    assertEquals("Expected middle name to be 'Adam'", "Adam", personName.getMiddleName());
    assertEquals("Expected family name to be 'Smith'", "Smith", personName.getFamilyName());
    assertEquals("Expected family name2 to be 'Doe'", "Doe", personName.getFamilyName2());

    // Test case for a name with three parts, checking if the middle name is assigned or concatenated with the last name
    personName = Context.getPersonService().parsePersonName("John Adam Smith");
    assertEquals("John", personName.getGivenName());
    assertEquals("Expected middle name to be 'Adam' or an empty string if the parsing logic has changed", "Adam", personName.getMiddleName());
    assertEquals("Smith", personName.getFamilyName());
    assertNull("Expected family name2 to be null for a three-part name", personName.getFamilyName2());

    // Test case for a name with two parts, to ensure no middle name is assumed
    personName = Context.getPersonService().parsePersonName("John Smith");
    assertEquals("John", personName.getGivenName());
    assertEquals("Expected no middle name for a two-part name", "", personName.getMiddleName());
    assertEquals("Smith", personName.getFamilyName());
    assertNull("Expected family name2 to be null for a two-part name", personName.getFamilyName2());
}