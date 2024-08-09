static String openPre(final Nodes n, final int i) {
    return "db:open-pre('" + n.data.meta.name + "', " + n.list[i] + ")";
  }