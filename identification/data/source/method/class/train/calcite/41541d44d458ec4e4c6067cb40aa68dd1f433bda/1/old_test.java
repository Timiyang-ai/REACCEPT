@Test public void testClosure() {
    final SortedMap<Integer, BitSet> empty = Maps.newTreeMap();
    assertThat(BitSets.closure(empty), equalTo(empty));

    // Currently you need an entry for each position, otherwise you get an NPE.
    // We should fix that.
    final SortedMap<Integer, BitSet> map = Maps.newTreeMap();
    map.put(0, BitSets.of(3));
    map.put(1, BitSets.of());
    map.put(2, BitSets.of(7));
    map.put(3, BitSets.of(4, 12));
    map.put(4, BitSets.of());
    map.put(5, BitSets.of());
    map.put(6, BitSets.of());
    map.put(7, BitSets.of());
    map.put(8, BitSets.of());
    map.put(9, BitSets.of());
    map.put(10, BitSets.of());
    map.put(11, BitSets.of());
    map.put(12, BitSets.of());
    String original = map.toString();
    assertThat(BitSets.closure(map).toString(),
        equalTo(
            "{0={3, 4, 12}, 1={}, 2={7}, 3={3, 4, 12}, 4={4, 12}, 5={}, 6={}, 7={7}, 8={}, 9={}, 10={}, 11={}, 12={4, 12}}"));
    assertThat("argument modified", map.toString(), equalTo(original));
  }