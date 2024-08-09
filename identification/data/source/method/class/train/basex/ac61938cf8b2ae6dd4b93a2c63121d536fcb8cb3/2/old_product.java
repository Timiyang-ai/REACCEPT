private Item replace(final byte[] val, final QueryContext ctx) throws QueryException {
    final byte[] rep = checkStr(expr[2], ctx);
    for(int i = 0; i < rep.length; ++i) {
      if(rep[i] == '\\') {
        if(i + 1 == rep.length || rep[i + 1] != '\\' && rep[i + 1] != '$')
          FUNREPBS.thrw(info);
        ++i;
      }
      if(rep[i] == '$' && (i == 0 || rep[i - 1] != '\\') &&
        (i + 1 == rep.length || !digit(rep[i + 1]))) FUNREPDOL.thrw(info);
    }

    final Pattern p = pattern(expr[1], expr.length == 4 ? expr[3] : null, ctx);
    if(p.pattern().isEmpty()) REGROUP.thrw(info);

    String r = string(rep);
    if((p.flags() & Pattern.LITERAL) != 0) {
      r = SLASH.matcher(BSLASH.matcher(r).replaceAll("\\\\\\\\")).replaceAll("\\\\\\$");
    }

    try {
      return Str.get(p.matcher(string(val)).replaceAll(r));
    } catch(final Exception ex) {
      final String m = ex.getMessage();
      if(m.contains("No group")) REGROUP.thrw(info);
      throw REGPAT.thrw(info, m);
    }
  }