  @Test
  public void merge() {
    PropertyKey newKey = new PropertyKey.Builder("mergeNew").setDefaultValue("value3").build();
    Properties sysProp = new Properties();
    sysProp.put(mKeyWithValue, "value1");
    sysProp.put(mKeyWithoutValue, "value2");
    mProperties.merge(sysProp, Source.SYSTEM_PROPERTY);
    assertEquals(Source.SYSTEM_PROPERTY, mProperties.getSource(mKeyWithValue));
    assertEquals(Source.SYSTEM_PROPERTY, mProperties.getSource(mKeyWithoutValue));
    assertEquals(Source.DEFAULT, mProperties.getSource(newKey));
    assertEquals("value1", mProperties.get(mKeyWithValue));
    assertEquals("value2", mProperties.get(mKeyWithoutValue));
    assertEquals("value3", mProperties.get(newKey));
  }