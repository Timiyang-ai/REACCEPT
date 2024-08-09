  @Test
  public void isSet() {
    assertTrue(mProperties.isSet(mKeyWithValue));
    assertFalse(mProperties.isSet(mKeyWithoutValue));
    mProperties.remove(mKeyWithValue);
    mProperties.put(mKeyWithoutValue, "value", Source.RUNTIME);
    assertTrue(mProperties.isSet(mKeyWithValue));
    assertTrue(mProperties.isSet(mKeyWithoutValue));
  }