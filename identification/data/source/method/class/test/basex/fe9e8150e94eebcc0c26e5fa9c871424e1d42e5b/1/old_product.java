private static String replace(final String cls, final char incl) {
    final int[] v = cls.startsWith("Is") ? MAP.get(cls.substring(2)) : null;
    if(v == null) return "\\" + incl + "{" + cls + "}";
    final TokenBuilder tb = new TokenBuilder().add('[');
    if(incl == 'P') tb.add("!");
    for(int i = 0; i < v.length;) {
      tb.add(v[i++]);
      tb.add('-');
      tb.add(v[i++]);
    }
    return tb.add(']').toString();
  }