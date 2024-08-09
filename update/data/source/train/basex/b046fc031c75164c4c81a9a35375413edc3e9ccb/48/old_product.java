private Iter list(final QueryContext qc) throws QueryException, IOException {
    final Path dir = checkPath(0, qc).toRealPath();
    final boolean rec = optionalBool(1, qc);
    final Pattern pat = exprs.length == 3 ? Pattern.compile(IOFile.regex(
        string(checkStr(exprs[2], qc))), Prop.CASE ? 0 : Pattern.CASE_INSENSITIVE) : null;

    final TokenList list = new TokenList();
    list(dir.getNameCount(), dir, list, rec, pat);
    return StrSeq.get(list).iter();
  }