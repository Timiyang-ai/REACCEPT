private ANode entries(final QueryContext ctx) throws QueryException {
    final String file = string(checkStr(expr[0], ctx));

    // check file path
    final IO path = IO.get(file);
    if(!path.exists()) ZIPNOTFOUND.thrw(input, file);
    // loop through file
    ZipFile zf = null;
    try {
      zf = new ZipFile(file);
      // create result node
      final FElem root = new FElem(E_FILE, ZIP, ZIPURI);
      root.atts.add(new FAttr(A_HREF, token(path.path()), root));
      createEntries(paths(zf).iterator(), root, "");
      return root;
    } catch(final IOException ex) {
      throw ZIPFAIL.thrw(input, ex.getMessage());
    } finally {
      if(zf != null) try { zf.close(); } catch(final IOException e) { }
    }
  }