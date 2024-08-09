  @Test
  public void removeTest2() {
    IntIntMap map = createMap();
    for (int i = 0; i < 10000; i++) {
      map.put(i, i + 1);
    }
    for (int i = 0; i < 10000; i += 3) {
      map.remove(i);
    }
    for (int i = 0; i < 10000; i += 3) {
      Assert.assertTrue(!map.containsKey(i));
    }
    for (int i = 0; i < 10000; i++) {
      map.put(i, i + 1);
    }
    for (int i = 0; i < 10000; i += 3) {
      Assert.assertTrue(map.containsKey(i));
    }
  }