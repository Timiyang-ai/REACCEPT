static String openPre(final Nodes n, final int i) {
    System.out.println("? ");
    return Function.DBOPENPRE.get(null, Str.get(n.data.meta.name),
        Itr.get(n.list[i])).toString();
  }