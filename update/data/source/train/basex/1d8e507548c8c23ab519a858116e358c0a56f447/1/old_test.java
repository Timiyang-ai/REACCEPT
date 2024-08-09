@Test
  public void testSystem() throws BaseXException {
    // wrong arguments
    args("db:system");

    // standard test
    contains("db:system()", "ON");
  }