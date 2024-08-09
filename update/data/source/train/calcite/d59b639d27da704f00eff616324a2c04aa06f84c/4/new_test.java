@Test public void testIterator() {
    final ChunkList<String> list = new ChunkList<>();
    list.add("a");
    list.add("b");
    final ListIterator<String> listIterator = list.listIterator(0);
    try {
      listIterator.remove();
      fail("excepted exception");
    } catch (IllegalStateException e) {
      // ok
    }
    listIterator.next();
    listIterator.remove();
    assertThat(list.size(), is(1));
    assertThat(listIterator.hasNext(), is(true));
    listIterator.next();
    listIterator.remove();
    assertThat(list.size(), is(0));
    assertThat(listIterator.hasNext(), is(false));
  }