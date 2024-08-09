static Item[] slice(final Item[] items, final int from, final int to) {
    final Item[] out = new Item[to - from];
    final int in0 = Math.max(0, from), in1 = Math.min(to, items.length);
    final int out0 = Math.max(-from, 0);
    Array.copy(items, in0, in1 - in0, out, out0);
    return out;
  }