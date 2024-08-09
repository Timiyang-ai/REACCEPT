private void add(final String head, final StringList list) {
    final int runs = Math.max(1, gui.context.prop.num(Prop.RUNS));
    if(list.size() == 0) return;
    text.bold().add(head).norm().nline();
    final int is = list.size();
    for(int i = 0; i < is; ++i) {
      String line = list.get(i);
      if(list == strings) line = " " + QUERYSEP + line + ":  " +
        Performance.getTimer(stat.get(i) * 10000L * runs, runs);
      text.add(line).nline();
    }
    text.hline();
  }