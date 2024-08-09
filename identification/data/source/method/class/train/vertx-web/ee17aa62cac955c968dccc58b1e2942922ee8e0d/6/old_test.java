  @Test
  public void isValid() {
    NumericTypeValidator v = new NumericTypeValidator(Integer.class);
    v.isValid("1");
  }