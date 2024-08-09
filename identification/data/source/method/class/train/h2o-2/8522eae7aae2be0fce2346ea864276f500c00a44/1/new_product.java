public static int numSetBitsHex(String s) {
    // Look-up table for num set bits in 4-bit char
    final int[] bits_set = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};

    int nset = 0;
    for(int i = 0; i < s.length(); i++) {
      Character ch = s.charAt(i);
      if (ch == ',') {
        continue;
      }
      int x = Integer.parseInt(ch.toString(), 16);
      nset += bits_set[x];
    }
    return nset;
  }