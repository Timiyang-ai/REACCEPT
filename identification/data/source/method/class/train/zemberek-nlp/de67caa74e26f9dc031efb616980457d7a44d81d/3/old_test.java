  @Test
  public void getTest() {
    UIntMap<String> map = new UIntMap<>(1);
    map.put(1, "2");
    Assert.assertEquals("2", map.get(1));
    Assert.assertNull(map.get(2));
    map.put(1, "3");
    Assert.assertEquals("3", map.get(1));

    map = new UIntMap<>();
    for (int i = 0; i < 100000; i++) {
      map.put(i, String.valueOf(i + 1));
    }
    for (int i = 0; i < 100000; i++) {
      Assert.assertEquals(String.valueOf(i + 1), map.get(i));
    }
  }