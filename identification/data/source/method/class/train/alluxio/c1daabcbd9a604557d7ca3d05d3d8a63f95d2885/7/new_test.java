  @Test
  public void remove() {
    mProperties.remove(mKeyWithValue);
    assertEquals(mKeyWithValue.getDefaultValue(), mProperties.get(mKeyWithValue));
    assertEquals(Source.DEFAULT, mProperties.getSource(mKeyWithValue));
  }