  @Test
  public void put() {
    mProperties.put(mKeyWithValue, "value1", Source.SYSTEM_PROPERTY);
    mProperties.put(mKeyWithoutValue, "value2", Source.SYSTEM_PROPERTY);
    assertEquals("value1", mProperties.get(mKeyWithValue));
    assertEquals("value2", mProperties.get(mKeyWithoutValue));

    mProperties.put(mKeyWithValue, "valueLowerPriority", Source.siteProperty(""));
    assertEquals("value1", mProperties.get(mKeyWithValue));
    mProperties.put(mKeyWithValue, "valueSamePriority", Source.SYSTEM_PROPERTY);
    assertEquals("valueSamePriority", mProperties.get(mKeyWithValue));
    mProperties.put(mKeyWithValue, "valueHigherPriority", Source.RUNTIME);
    assertEquals("valueHigherPriority", mProperties.get(mKeyWithValue));
  }