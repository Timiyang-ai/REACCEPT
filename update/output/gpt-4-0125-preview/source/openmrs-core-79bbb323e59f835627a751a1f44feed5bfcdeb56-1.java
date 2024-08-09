@Test
public void parsePersonName_shouldHandleMultipleLastNameParts() throws Exception {
    PersonName pname = Context.getPersonService().parsePersonName("John Adam Smith Doe ");
    assertEquals("John", pname.getGivenName());
    assertEquals("Adam", pname.getMiddleName());
    assertEquals("Smith", pname.getFamilyName());
    assertEquals("Doe", pname.getFamilyName2());
}