  static void fill(IcedBitSet bs, Integer[] idx) {
    Random rng = new Random();
    for (int i = 0; i < idx.length; ++i) {
      idx[i] = rng.nextInt(bs.size());
      bs.set(idx[i]);
    }
  }