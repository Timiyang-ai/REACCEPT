@Test 
public final void testFinish() {
    ItemList il = new ItemList(Itr.ZERO);
    for(int i = 0; i < CAP - 1; i++) {
      il.add(Itr.ZERO);
    }
    Object[] finishedArray = il.finish(); // Changed Item[] to Object[] to resolve the compilation error
    assertEquals(CAP, finishedArray.length); 
    assertEquals(0, il.size()); // After calling finish, size should be reset to 0
}