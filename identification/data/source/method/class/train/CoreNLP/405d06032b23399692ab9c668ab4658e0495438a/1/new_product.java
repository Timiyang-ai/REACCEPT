public void printTree(final Tree t, final String id, final PrintWriter pw) {
    final boolean inXml = propertyToBoolean(options, "xml");
    if (t == null) {
      // Parsing didn't succeed.
      if (inXml) {
        pw.print("<s");
        if (id != null && ! "".equals(id)) {
          pw.print(" id=\"" + XMLUtils.escapeXML(id) + '\"');
        }
        pw.println(" skipped=\"true\"/>");
        pw.println();
      } else {
        pw.println("SENTENCE_SKIPPED_OR_UNPARSABLE");
      }
    } else {
      if (inXml) {
        pw.print("<s");
        if (id != null && ! "".equals(id)) {
          pw.print(" id=\"" + XMLUtils.escapeXML(id) + '\"');
        }
        pw.println(">");
      }
      printTreeInternal(t, pw, inXml);
      if (inXml) {
        pw.println("</s>");
        pw.println();
      }
    }
  }