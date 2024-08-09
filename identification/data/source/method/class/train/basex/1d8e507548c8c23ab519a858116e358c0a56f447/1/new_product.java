public String info() {
    final TokenBuilder tb = new TokenBuilder();
    tb.addExt(SRVDATABASES, list.size());
    tb.add(list.size() != 0 ? COL : DOT);
    for(PData d : list) {
      tb.add(NL + LI + d.data.meta.name + " (" + d.pins + "x)");
    }
    return tb.toString();
  }