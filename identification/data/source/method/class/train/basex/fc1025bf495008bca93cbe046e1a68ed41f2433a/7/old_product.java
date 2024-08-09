public synchronized String info() {
    final TokenBuilder tb = new TokenBuilder();
    tb.addExt(EVENTS_X, size()).add(size() != 0 ? COL : DOT);

    final String[] names = keySet().toArray(new String[size()]);
    Arrays.sort(names);
    for(final String n : names) tb.add(NL).add(LI).add(n);
    return tb.toString();
  }