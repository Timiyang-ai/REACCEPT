private Str execute(final QueryContext qc) throws QueryException {
    final ClientSession cs = session(qc, false);
    final String cmd = Token.string(toToken(exprs[1], qc));

    try {
      final ArrayOutput ao = new ArrayOutput();
      cs.setOutputStream(ao);
      cs.execute(cmd);
      cs.setOutputStream(null);
      return Str.get(ao.finish());
    } catch(final BaseXException ex) {
      throw BXCL_COMMAND_X.get(info, ex);
    } catch(final IOException ex) {
      throw BXCL_COMM_X.get(info, ex);
    }
  }