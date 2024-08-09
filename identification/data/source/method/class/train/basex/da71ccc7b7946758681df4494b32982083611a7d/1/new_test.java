@Test
  public void removeTest() {
    final int n = 100;
    XQArray seq = XQArray.empty();

    for(int k = 0; k < n; k++) {
      assertEquals(k, seq.arraySize());
      for(int i = 0; i < k; i++) {
        final XQArray seq2 = seq.remove(i, qc);
        assertEquals(k - 1, seq2.arraySize());

        final Iterator<Value> iter = seq2.iterator(0);
        for(int j = 0; j < k - 1; j++) {
          assertTrue(iter.hasNext());
          assertEquals(j < i ? j : j + 1, ((Int) iter.next()).itr());
        }
        assertFalse(iter.hasNext());
      }
      seq = seq.snoc(Int.get(k));
    }
  }