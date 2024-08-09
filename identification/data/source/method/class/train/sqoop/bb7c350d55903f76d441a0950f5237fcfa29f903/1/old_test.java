@Test
  public void testGetNestedProperties() {
    Map<String, String> options = new HashMap<String, String>();
    options.put("sqooptest1", "value");
    options.put("sqooptest2", "value");
    options.put("testsqoop1", "value");
    options.put("testsqoop1", "value");
    MapContext mc = new MapContext(options);
    Map<String, String> result = mc.getNestedProperties("sqoop");
    Assert.assertEquals(2, result.size());
    Assert.assertTrue(result.containsKey("test1"));
    Assert.assertTrue(result.containsKey("test2"));
    Assert.assertFalse(result.containsKey("testsqoop1"));
    Assert.assertFalse(result.containsKey("testsqoop2"));
  }