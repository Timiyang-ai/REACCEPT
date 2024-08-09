@Test public void testClosure() {
    final SortedMap<Integer, BitSet> empty = Maps.newTreeMap();
    assertThat(BitSets.closure(empty), equalTo(empty));

    // Map with an an entry for each position.
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
    final String original = map.toString();
    final String expected =
        "{0={3, 4, 12}, 1={}, 2={7}, 3={3, 4, 12}, 4={4, 12}, 5={}, 6={}, 7={7}, 8={}, 9={}, 10={}, 11={}, 12={4, 12}}";
    assertThat(BitSets.closure(map).toString(), equalTo(expected));
    assertThat("argument modified", map.toString(), equalTo(original));

    // Now a similar map with missing entries. Same result.
    final SortedMap<Integer, BitSet> map2 = Maps.newTreeMap();
    map2.put(0, BitSets.of(3));
    map2.put(2, BitSets.of(7));
    map2.put(3, BitSets.of(4, 12));
    map2.put(9, BitSets.of());
    final String original2 = map2.toString();
    assertThat(BitSets.closure(map2).toString(), equalTo(expected));
    assertThat("argument modified", map2.toString(), equalTo(original2));
  }