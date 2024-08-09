@Test
  public void testGet() {
    BytesRefBuilder ref = new BytesRefBuilder();
    BytesRef scratch = new BytesRef();
    int num = atLeast(2);
    for (int j = 0; j < num; j++) {
      Map<String, Integer> strings = new HashMap<>();
      int uniqueCount = 0;
      for (int i = 0; i < 797; i++) {
        String str;
        do {
          str = TestUtil.randomRealisticUnicodeString(random(), 1000);
        } while (str.length() == 0);
        ref.copyChars(str);
        int count = hash.size();
        int key = hash.add(ref.get());
        if (key >= 0) {
          assertNull(strings.put(str, Integer.valueOf(key)));
          assertEquals(uniqueCount, key);
          uniqueCount++;
          assertEquals(hash.size(), count + 1);
        } else {
          assertTrue((-key)-1 < count);
          assertEquals(hash.size(), count);
        }
      }
      for (Entry<String, Integer> entry : strings.entrySet()) {
        ref.copyChars(entry.getKey());
        assertEquals(ref.get(), hash.get(entry.getValue().intValue(), scratch));
      }
      hash.clear();
      assertEquals(0, hash.size());
      hash.reinit();
    }
  }