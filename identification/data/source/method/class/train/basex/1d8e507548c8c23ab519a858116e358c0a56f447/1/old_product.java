public String info() {
    final TokenBuilder tb = new TokenBuilder();
    tb.addExt(SRVDATABASES, size);
    tb.add(size != 0 ? COL : DOT);
    for(int i = 0; i < size; ++i) {
      tb.add(NL + LI + data[i].meta.name + " (" + pins[i] + "x)");
    }
    return tb.toString();
  }