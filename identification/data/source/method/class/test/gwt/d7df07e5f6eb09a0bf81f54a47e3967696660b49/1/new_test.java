  public void test_getImplName_default() {
    String basicName = "MyBinderImpl";
    String result = stub.getImplName(basicName);
    assertEquals(basicName, result);
  }