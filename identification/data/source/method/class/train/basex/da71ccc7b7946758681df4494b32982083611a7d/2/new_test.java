@Test
  public void insertTest() {
    final int n = 1_000;
    XQArray seq = XQArray.empty();

    for(int i = 0; i < n; i++) seq = seq.snoc(Int.get(i));
    assertEquals(n, seq.arraySize());

    final Int val = Int.get(n);
    for(int i = 0; i <= n; i++) {
      final XQArray seq2 = seq.insertBefore(i, val, qc);
      assertEquals(n, ((Int) seq2.get(i)).itr());
      assertEquals(n + 1L, seq2.arraySize());
      for(int j = 0; j < n; j++) {
        assertEquals(j, ((Int) seq2.get(j < i ? j : j + 1)).itr());
      }
    }
  }