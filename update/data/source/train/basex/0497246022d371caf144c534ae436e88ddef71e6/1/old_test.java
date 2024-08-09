@Test
  public void concatTest() {
    Array seq1 = Array.empty();
    Array seq2 = Array.empty();
    final int n = 200_000;
    for(int i = 0; i < n; i++) {
      final Value val = Int.get(i);
      seq1 = seq1.cons(val);
      seq2 = seq2.snoc(val);
    }

    assertEquals(n, seq1.arraySize());
    assertEquals(n, seq2.arraySize());
    final Array seq = seq1.concat(seq2);
    assertEquals(2 * n, seq.arraySize());

    for(int i = 0; i < 2 * n; i++) {
      final int diff = i - n, j = diff < 0 ? -(diff + 1) : diff;
      assertEquals(j, ((Int) seq.get(i)).itr());
    }
  }