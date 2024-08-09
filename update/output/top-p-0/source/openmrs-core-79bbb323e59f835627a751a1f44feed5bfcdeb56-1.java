@Test
public void parsePersonName_shouldHandleNameWithFourPartsAndAssignLastName2() throws Exception {
    // Test parsing a name with four parts, expecting lastName2 to be set
    PersonName pname = Context.getPersonService().parsePersonName("John Adam Smith Doe");
    assertEquals("John", pname.getGivenName());
    assertEquals("Adam", pname.getMiddleName());
    assertEquals("Smith", pname.getFamilyName());
    assertEquals("Doe", pname.getFamilyName2());

    // Test parsing a name with trailing whitespace, now that trimming is explicitly part of the method
    pname = Context.getPersonService().parsePersonName(" Jane Doe ");
    assertEquals("Jane", pname.getGivenName());
    assertEquals("", pname.getMiddleName()); // Expecting middle name to be empty
    assertEquals("Doe", pname.getFamilyName());
    assertNull("Expected lastName2 to be null for names with less than four parts", pname.getFamilyName2());

    // Test parsing a name with three parts, which should not set lastName2
    pname = Context.getPersonService().parsePersonName("John Adam Doe");
    assertEquals("John", pname.getGivenName());
    assertEquals("Adam", pname.getMiddleName());
    assertEquals("Doe", pname.getFamilyName());
    assertNull("Expected lastName2 to be null for names with exactly three parts", pname.getFamilyName2());
}