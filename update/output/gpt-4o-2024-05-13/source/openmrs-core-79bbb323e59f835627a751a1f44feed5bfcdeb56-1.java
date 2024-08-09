@Test
public void parsePersonName_shouldNotFailWhenEndingWithWhitespace() throws Exception {
    // Test with a name ending with whitespace
    PersonName pname = Context.getPersonService().parsePersonName("John ");
    assertEquals("John", pname.getGivenName());
    assertNull(pname.getFamilyName());
    assertNull(pname.getMiddleName());
    assertNull(pname.getFamilyName2());

    // Test with a name containing a middle name and ending with whitespace
    pname = Context.getPersonService().parsePersonName("John Adam ");
    assertEquals("John", pname.getGivenName());
    assertEquals("Adam", pname.getMiddleName());
    assertNull(pname.getFamilyName());
    assertNull(pname.getFamilyName2());

    // Test with a name containing a middle name and last name ending with whitespace
    pname = Context.getPersonService().parsePersonName("John Adam Smith ");
    assertEquals("John", pname.getGivenName());
    assertEquals("Adam", pname.getMiddleName());
    assertEquals("Smith", pname.getFamilyName());
    assertNull(pname.getFamilyName2());

    // Test with a name containing two last names
    pname = Context.getPersonService().parsePersonName("John Adam Smith Doe");
    assertEquals("John", pname.getGivenName());
    assertEquals("Adam", pname.getMiddleName());
    assertEquals("Smith", pname.getFamilyName());
    assertEquals("Doe", pname.getFamilyName2());
}