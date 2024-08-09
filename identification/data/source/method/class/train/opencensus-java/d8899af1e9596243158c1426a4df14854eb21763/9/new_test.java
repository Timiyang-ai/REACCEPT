  @Test(expected = NullPointerException.class)
  public void fromDescription_NullDescription() {
    Annotation.fromDescription(null);
  }