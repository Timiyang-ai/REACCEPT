public static String diffLines(List<String> lines1, List<String> lines2) {
    Diff differencer = new Diff(lines1, lines2);
    List<Difference> differences = differencer.diff();
    StringWriter sw = new StringWriter();
    int offset = 0;
    for (Difference d : differences) {
      final int as = d.getAddedStart() + 1;
      final int ae = d.getAddedEnd() + 1;
      final int ds = d.getDeletedStart() + 1;
      final int de = d.getDeletedEnd() + 1;
      if (ae == 0) {
        if (de == 0) {
          // no change
        } else {
          // a deletion: "<ds>,<de>d<as>"
          sw.append(String.valueOf(ds));
          if (de > ds) {
            sw.append(",").append(String.valueOf(de));
          }
          sw.append("d").append(String.valueOf(as - 1)).append('\n');
          for (int i = ds - 1; i < de; ++i) {
            sw.append("< ").append(lines1.get(i)).append('\n');
          }
        }
      } else {
        if (de == 0) {
          // an addition: "<ds>a<as,ae>"
          sw.append(String.valueOf(ds - 1)).append("a").append(
              String.valueOf(as));
          if (ae > as) {
            sw.append(",").append(String.valueOf(ae));
          }
          sw.append('\n');
          for (int i = as - 1; i < ae; ++i) {
            sw.append("> ").append(lines2.get(i)).append('\n');
          }
        } else {
          // a change: "<ds>,<de>c<as>,<ae>
          sw.append(String.valueOf(ds));
          if (de > ds) {
            sw.append(",").append(String.valueOf(de));
          }
          sw.append("c").append(String.valueOf(as));
          if (ae > as) {
            sw.append(",").append(String.valueOf(ae));
          }
          sw.append('\n');
          for (int i = ds - 1; i < de; ++i) {
            sw.append("< ").append(lines1.get(i)).append('\n');
          }
          sw.append("---\n");
          for (int i = as - 1; i < ae; ++i) {
            sw.append("> ").append(lines2.get(i)).append('\n');
          }
          offset = offset + (ae - as) - (de - ds);
        }
      }
    }
    return sw.toString();
  }