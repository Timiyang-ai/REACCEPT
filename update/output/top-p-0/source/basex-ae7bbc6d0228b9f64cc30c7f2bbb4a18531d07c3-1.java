@Test 
public final void testFinish() {
  ItemList il = new ItemList(Itr.ZERO);
  for(int i = 0; i < CAP - 1; i++) {
    il.add(Itr.ZERO);
  }
  // Since finish() now asserts the list is not empty, we no longer need to check for empty list scenarios
  Item[] finishedArray = il.finish();
  assertEquals(CAP, finishedArray.length); 
  // After calling finish(), the size of il should be reset to 0
  assertEquals(0, il.size());
  // We can also add a check to ensure that the values array within il is reset to a new array of size CAP
  // This might require reflection or exposing the internal state through a package-private method for testing purposes
}