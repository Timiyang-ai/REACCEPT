@Test
  public void testSort() {
    BytesRef ref = new BytesRef();
    int num = atLeast(2);
    for (int j = 0; j < num; j++) {
      SortedSet<String> strings = new TreeSet<String>();
      for (int i = 0; i < 797; i++) {
        String str;
        do {
          str = _TestUtil.randomRealisticUnicodeString(random(), 1000);
        } while (str.length() == 0);
        ref.copyChars(str);
        hash.add(ref);
        strings.add(str);
      }
      // We use the UTF-16 comparator here, because we need to be able to
      // compare to native String.compareTo() [UTF-16]:
      int[] sort = hash.sort(BytesRef.getUTF8SortedAsUTF16Comparator());
      assertTrue(strings.size() < sort.length);
      int i = 0;
      BytesRef scratch = new BytesRef();
      for (String string : strings) {
        ref.copyChars(string);
        assertEquals(ref, hash.get(sort[i++], scratch));
      }
      hash.clear();
      assertEquals(0, hash.size());
      hash.reinit();

    }
  }