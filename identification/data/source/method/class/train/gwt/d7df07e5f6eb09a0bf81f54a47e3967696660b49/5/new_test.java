  public void test_handleUIObject_default() {
    WriterStatements writer = new WriterStatements();
    stub.handleUIObject(writer, null, "myField");
    assertEquals(0, writer.statements.size());
  }