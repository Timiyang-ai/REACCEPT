@Test 
  public final void testFinish() {
    ItemList il = new ItemList(Itr.ZERO);
    for(int i = 0; i < CAP - 1; i++) {
      il.add(Itr.ZERO);
    }
    Object[] finishedArray = il.finish(); // Adjusted to use Object[] to avoid compilation error due to unknown Item class
    assertEquals(CAP, finishedArray.length); 
    assertEquals(0, il.size());
  }