protected final void execute(final Command cmd, final boolean info) throws IOException {
    final Session ss = session();
    ss.execute(cmd);
    if(newline && cmd instanceof XQuery) out.write(token(NL));
    if(info) Util.out(ss.info());
  }