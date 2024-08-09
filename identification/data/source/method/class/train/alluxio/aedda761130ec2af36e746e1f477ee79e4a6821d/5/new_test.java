  @Test
  public void fromString() throws Exception {
    assertEquals(PropertyKey.VERSION, PropertyKey.fromString(PropertyKey.VERSION.toString()));
    PropertyKey.fromString(PropertyKey.VERSION.toString());
    assertEquals(mTestProperty, PropertyKey.fromString("alluxio.test.property.alias1"));
    assertEquals(mTestProperty, PropertyKey.fromString("alluxio.test.property.alias2"));
    assertEquals(mTestProperty, PropertyKey.fromString(mTestProperty.toString()));
  }