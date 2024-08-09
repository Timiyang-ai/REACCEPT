private Str execute(final QueryContext qc) throws QueryException {
    final ClientSession cs = session(qc, false);
    final String cmd = Token.string(checkStr(exprs[1], qc));

    try {
      final ArrayOutput ao = new ArrayOutput();
      cs.setOutputStream(ao);
      cs.execute(cmd);
      cs.setOutputStream(null);
      return Str.get(ao.finish());
    } catch(final BaseXException ex) {
      throw BXCL_COMMAND.get(info, ex);
    } catch(final IOException ex) {
      throw BXCL_COMM.get(info, ex);
    }
  }