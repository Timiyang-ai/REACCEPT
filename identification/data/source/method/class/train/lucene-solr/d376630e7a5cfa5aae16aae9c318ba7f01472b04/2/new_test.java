@Test
  public void testAdd() {
    BytesRef ref = new BytesRef();
    BytesRef scratch = new BytesRef();
    int num = atLeast(2);
    for (int j = 0; j < num; j++) {
      Set<String> strings = new HashSet<String>();
      int uniqueCount = 0;
      for (int i = 0; i < 797; i++) {
        String str;
        do {
          str = _TestUtil.randomRealisticUnicodeString(random, 1000);
        } while (str.length() == 0);
        ref.copyChars(str);
        int count = hash.size();
        int key = hash.add(ref);

        if (key >=0) {
          assertTrue(strings.add(str));
          assertEquals(uniqueCount, key);
          assertEquals(hash.size(), count + 1);
          uniqueCount++;
        } else {
          assertFalse(strings.add(str));
          assertTrue((-key)-1 < count);
          assertEquals(str, hash.get((-key)-1, scratch).utf8ToString());
          assertEquals(count, hash.size());
        }
      }
      
      assertAllIn(strings, hash);
      hash.clear();
      assertEquals(0, hash.size());
      hash.reinit();
    }
  }