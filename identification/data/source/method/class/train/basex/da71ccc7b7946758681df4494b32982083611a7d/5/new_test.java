@Test
  public void testSlice() {
    XQArray arr = XQArray.empty();
    for(int len = 0; len < 180; len++) {
      assertEquals(len, arr.arraySize());
      for(int pos = 0; pos < len; pos++) {
        for(int k = 0; k <= len - pos; k++) {
          final XQArray sub = arr.subArray(pos, k, qc);
          assertEquals(k, sub.arraySize());
          sub.checkInvariants();
          final Iterator<Value> iter = sub.iterator(0);
          for(int i = 0; i < k; i++) {
            final long res = ((Int) iter.next()).itr();
            if(res != pos + i) {
              fail("Wrong value: " + res + " vs. " + (pos + i));
            }
          }
        }
      }
      arr = arr.snoc(Int.get(len));
    }
  }