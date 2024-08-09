@Test public void testAsList() {
    final List<ImmutableBitSet> list = getSortedList();
    for (ImmutableBitSet bitSet : list) {
      final List<Integer> list1 = bitSet.toList();
      final List<Integer> listView = bitSet.asList();
      assertThat(list1.size(), equalTo(bitSet.cardinality()));
      assertThat(list1.toString(), equalTo(listView.toString()));
      assertTrue(list1.equals(listView));
      assertThat(list1.hashCode(), equalTo(listView.hashCode()));
    }
  }