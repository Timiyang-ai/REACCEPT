@Test
    public void testGet() {
        BytesRef ref = new BytesRef();
        BytesRef scratch = new BytesRef();
        int num = scaledRandomIntBetween(2, 20);
        for (int j = 0; j < num; j++) {
            Map<String, Long> strings = new HashMap<>();
            int uniqueCount = 0;
            for (int i = 0; i < 797; i++) {
                String str;
                do {
                    str = _TestUtil.randomRealisticUnicodeString(getRandom(), 1000);
                } while (str.length() == 0);
                ref.copyChars(str);
                long count = hash.size();
                long key = hash.add(ref);
                if (key >= 0) {
                    assertNull(strings.put(str, Long.valueOf(key)));
                    assertEquals(uniqueCount, key);
                    uniqueCount++;
                    assertEquals(hash.size(), count + 1);
                } else {
                    assertTrue((-key)-1 < count);
                    assertEquals(hash.size(), count);
                }
            }
            for (Entry<String, Long> entry : strings.entrySet()) {
                ref.copyChars(entry.getKey());
                assertEquals(ref, hash.get(entry.getValue().longValue(), scratch));
            }
            newHash();
        }
        hash.release();
    }