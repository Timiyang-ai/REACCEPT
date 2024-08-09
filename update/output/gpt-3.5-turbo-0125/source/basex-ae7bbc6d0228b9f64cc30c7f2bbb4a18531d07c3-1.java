@Test 
public final void testToArray() {
    ItemList il = new ItemList(Itr.ZERO);
    for(int i = 0; i < CAP - 1; i++) {
        il.add(Itr.ZERO);
    }
    assertEquals(CAP, il.finish().length); 
    assertEquals(il.size(), il.finish().length);
}