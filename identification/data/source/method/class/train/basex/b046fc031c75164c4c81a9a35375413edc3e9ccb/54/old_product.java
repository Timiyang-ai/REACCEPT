private Item unparsedText(final QueryContext qc, final boolean check) throws QueryException {
    checkCreate(qc);
    final byte[] path = checkStr(exprs[0], qc);
    final IO base = sc.baseIO();

    String enc = null;
    try {
      if(base == null) throw STBASEURI.get(info);
      enc = checkEncoding(1, WHICHENC, qc);

      final String p = string(path);
      if(p.indexOf('#') != -1) throw FRAGID.get(info, p);
      if(!Uri.uri(p).isValid()) throw INVURL.get(info, p);

      IO io = base.merge(p);
      final String[] rp = qc.resources.texts.get(io.path());
      if(rp != null && rp.length > 0) {
        io = IO.get(rp[0]);
        if(rp.length > 1) enc = rp[1];
      }
      if(!io.exists()) throw RESNF.get(info, p);

      final InputStream is = io.inputStream();
      try {
        final TextInput ti = new TextInput(io).encoding(enc).validate(true);
        if(!check) return Str.get(ti.content());
        while(ti.read() != -1);
        return Bln.TRUE;
      } finally {
        is.close();
      }
    } catch(final QueryException ex) {
      if(check && !ex.err().is(ErrType.XPTY)) return Bln.FALSE;
      throw ex;
    } catch(final IOException ex) {
      if(check) return Bln.FALSE;
      if(ex instanceof InputException) {
        final boolean inv = ex instanceof EncodingException || enc != null;
        throw (inv ? INVCHARS : WHICHCHARS).get(info, ex);
      }
      throw RESNF.get(info, path);
    }
  }