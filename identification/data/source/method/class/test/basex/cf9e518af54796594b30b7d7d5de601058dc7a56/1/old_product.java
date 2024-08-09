final byte[] transform(final QueryContext qc) throws QueryException {
    checkCreate(qc);
    final IO in = read(0, qc), xsl = read(1, qc);
    final Options opts = toOptions(2, new Options(), qc);
    final XsltOptions xopts = toOptions(3, new XsltOptions(), qc);

    final PrintStream tmp = System.err;
    final ArrayOutput ao = new ArrayOutput();
    try {
      System.setErr(new PrintStream(ao));
      return transform(in, xsl, opts.free(), xopts);
    } catch(final TransformerException ex) {
      Util.debug(ex);
      throw XSLT_ERROR_X.get(info, trim(utf8(ao.finish(), Prop.ENCODING)));
    } finally {
      System.setErr(tmp);
    }
  }