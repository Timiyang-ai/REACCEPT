  @Test
  public void getTest() {
    IntValueMap<Integer> table = new IntValueMap<>();
    table.put(1, 2);
    Assert.assertEquals(2, table.get(1));
    Assert.assertEquals(0, table.get(2));
    table.put(1, 3);
    Assert.assertEquals(3, table.get(1));

    table = new IntValueMap<>();
    for (int i = 0; i < 1000; i++) {
      table.put(i, i + 1);
    }
    for (int i = 0; i < 1000; i++) {
      Assert.assertEquals(i + 1, table.get(i));
    }
  }