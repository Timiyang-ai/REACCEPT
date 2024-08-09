@Test
  public void containsAllTest() {
    final StringList list1 = new StringList(new String[] {"A", "B", "C"}),
        list2 = new StringList(new String[] {"A", "C"});
    assertTrue("List1 does contain list2", list1.containsAll(list2));
    assertFalse("List2 does not contain list1.", list2.containsAll(list1));
  }