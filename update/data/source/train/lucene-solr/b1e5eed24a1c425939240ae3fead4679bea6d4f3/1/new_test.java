@Test
  public void testGet() {
    BytesRef ref = new BytesRef();
    BytesRef scratch = new BytesRef();
    for (int j = 0; j < 2 * RANDOM_MULTIPLIER; j++) {
      Map<String, Integer> strings = new HashMap<String, Integer>();
      for (int i = 0; i < 797; i++) {
        String str;
        do {
          str = _TestUtil.randomRealisticUnicodeString(random, 1000);
        } while (str.length() == 0);
        ref.copy(str);
        int count = hash.size();
        int key = hash.add(ref);
        if (key >= 0) {
          assertNull(strings.put(str, Integer.valueOf(key)));
          assertEquals(i, key);
          assertEquals(hash.size(), count + 1);
        } else {
          assertTrue((-key)-1 < count);
          assertEquals(hash.size(), count);
        }
      }
      for (Entry<String, Integer> entry : strings.entrySet()) {
        ref.copy(entry.getKey());
        assertEquals(ref, hash.get(entry.getValue().intValue(), scratch));
      }
      hash.clear();
      assertEquals(0, hash.size());
      hash.reinit();
    }
  }