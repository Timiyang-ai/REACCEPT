private ANode entries(final QueryContext ctx) throws QueryException {
    //final Uri uri = (Uri) checkType(expr[0].item(ctx, input), Type.URI);
    final String file = string(checkStr(expr[0], ctx));
    if(!IO.get(file).exists()) ZIPNOTFOUND.thrw(input, file);

    // traverse all zip entries and create intermediate map,
    // as zip entries are not sorted
    final TreeMap<String, FElem> map = new TreeMap<String, FElem>();
    ZipFile zf = null;
    try {
      zf = new ZipFile(file);
      final Enumeration<? extends ZipEntry> en = zf.entries();
      // loop through all files
      while(en.hasMoreElements()) {
        final ZipEntry ze = en.nextElement();
        final FElem e = new FElem(ze.isDirectory() ? E_DIR : E_ENTRY, null);
        final String name = ze.getName();
        e.atts.add(new FAttr(A_NAME, token(name), e));
        map.put(name, e);
      }
    } catch(final IOException ex) {
      throw ZIPFAIL.thrw(input, ex.getMessage());
    } finally {
      if(zf != null) try { zf.close(); } catch(final IOException e) { }
    }

    // create result node
    final FElem root = new FElem(E_FILE, ZIP, ZIPURI);
    root.atts.add(new FAttr(A_HREF, token(file), root));
    final Iterator<String> it = map.keySet().iterator();
    createEntries(map, it, root, "");
    return root;
  }