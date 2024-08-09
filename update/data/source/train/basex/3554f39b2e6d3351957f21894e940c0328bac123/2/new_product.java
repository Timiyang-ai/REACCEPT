private ANode entries(final QueryContext ctx) throws QueryException {
    //final Uri uri = (Uri) checkType(expr[0].item(ctx, input), Type.URI);
    final String file = IOFile.file(string(checkStr(expr[0], ctx)));
    if(!IO.get(file).exists()) ZIPNOTFOUND.thrw(input, file);

    // traverse all zip entries and create map (zip entries are not sorted)
    ZipInputStream zis = null;
    try {
      zis = new ZipInputStream(new BufferedInputStream(
          new FileInputStream(file)));
      final TreeMap<String, FElem> map = new TreeMap<String, FElem>();
      ZipEntry ze;
      while((ze = zis.getNextEntry()) != null) {
        final String name = ze.getName();
        final boolean dir = ze.isDirectory();
        final FElem e = new FElem(dir ? E_DIR : E_ENTRY, null);
        e.atts.add(new FAttr(A_NAME, token(name), e));
        map.put(name, e);
      }

      // create result node
      final FElem root = new FElem(E_FILE, ZIP, ZIPURI);
      root.atts.add(new FAttr(A_HREF, token(file), root));

      final Iterator<String> it = map.keySet().iterator();
      createNode(map, it, root, "");
      return root;
    } catch(final IOException ex) {
      throw ZIPFAIL.thrw(input, ex.getMessage());
    } finally {
      if(zis != null) try { zis.close(); } catch(final IOException e) { }
    }
  }