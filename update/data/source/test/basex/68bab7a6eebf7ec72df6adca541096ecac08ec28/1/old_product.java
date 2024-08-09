private void replace() throws IOException {
    final Performance perf = new Performance();
    final String path = in.readString();
    final StringBuilder sb = new StringBuilder(REPLACE + " ");
    if(!path.isEmpty()) sb.append(TO + ' ' + path + ' ');
    log.write(this, sb.append("[...]"));

    final DecodingInput di = new DecodingInput(in);
    try {
      final InputSource is = new InputSource(di);
      final String info = Replace.replace(path, is, context, true);
      info(true, info, perf);
    } catch(final BaseXException ex) {
      di.flush();
      info(false, ex.getMessage(), perf);
    }
    out.flush();
  }