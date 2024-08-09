private ANode entries(final QueryContext ctx) throws QueryException {
    final String file = string(checkStr(expr[0], ctx));

    // check file path
    final IOFile path = new IOFile(file);
    if(!path.exists()) ZIP_NOTFOUND.thrw(info, file);
    // loop through file
    ZipFile zf = null;
    try {
      zf = new ZipFile(file);
      // create result node
      final FElem root = new FElem(E_FILE, new Atts(ZIP, ZIPURI));
      root.add(new FAttr(A_HREF, token(path.path())));
      createEntries(paths(zf).iterator(), root, "");
      return root;
    } catch(final IOException ex) {
      throw ZIP_FAIL.thrw(info, ex.getMessage());
    } finally {
      if(zf != null) try { zf.close(); } catch(final IOException e) { }
    }
  }