@Test 
  public final void testToArray() {
    ItemList il = new ItemList(Itr.ZERO);
    for(int i = 0; i < CAP - 1; i++) {
      il.add(Itr.ZERO);
    }
    assertEquals(CAP, il.toArray().length); 
    assertEquals(il.size(), il.toArray().length);
  }