@Test
  public final void testAdd() {
    ItemList il = new ItemList(Itr.ZERO);

    for(int i = 0; i < CAP - 1; i++) {
      il.add(Itr.ZERO);
    }
  }