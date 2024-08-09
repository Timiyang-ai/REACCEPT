@Test 
public final void testFinish() {
    ItemList il = new ItemList(Itr.ZERO);
    for(int i = 0; i < CAP - 1; i++) {
        il.add(Itr.ZERO);
    }
    // Ensure the size is as expected before calling finish
    assertEquals(CAP, il.size());
    
    // Call the new finish method and verify the returned array
    Item[] items = il.finish();
    assertEquals(CAP, items.length);
    
    // Ensure the ItemList is reset after calling finish
    assertEquals(0, il.size());
    assertEquals(0, il.toArray().length);
}