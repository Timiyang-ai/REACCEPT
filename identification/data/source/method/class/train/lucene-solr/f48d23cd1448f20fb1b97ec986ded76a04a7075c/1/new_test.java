@Test
  public void testSort() {
    BytesRefBuilder ref = new BytesRefBuilder();
    int num = atLeast(2);
    for (int j = 0; j < num; j++) {

      // Sorts by unicode code point order (is there a simple way, e.g. a Collator?)
      SortedSet<String> strings = new TreeSet<>(TestUtil.STRING_CODEPOINT_COMPARATOR);
      for (int i = 0; i < 797; i++) {
        String str;
        do {
          str = TestUtil.randomRealisticUnicodeString(random(), 1000);
        } while (str.length() == 0);
        ref.copyChars(str);
        hash.add(ref.get());
        strings.add(str);
      }
      int[] sort = hash.sort();
      assertTrue(strings.size() < sort.length);
      int i = 0;
      BytesRef scratch = new BytesRef();
      for (String string : strings) {
        ref.copyChars(string);
        assertEquals(ref.get(), hash.get(sort[i++], scratch));
      }
      hash.clear();
      assertEquals(0, hash.size());
      hash.reinit();

    }
  }