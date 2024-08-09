private Item zipFile(final QueryContext ctx) throws QueryException {
    // check argument
    final ANode elm = (ANode) checkType(expr[0].item(ctx, info), NodeType.ELM);
    if(!elm.qname().eq(E_FILE)) ZIPUNKNOWN.thrw(info, elm.qname());
    // get file
    final String file = attribute(elm, A_HREF, true);

    // write zip file
    FileOutputStream fos = null;
    boolean ok = true;
    try {
      fos = new FileOutputStream(file);
      final ZipOutputStream zos =
        new ZipOutputStream(new BufferedOutputStream(fos));
      create(zos, elm.children(), "", null, ctx);
      zos.close();
    } catch(final IOException ex) {
      ok = false;
      ZIPFAIL.thrw(info, ex.getMessage());
    } finally {
      if(fos != null) {
        try { fos.close(); } catch(final IOException ex) { }
        if(!ok) new IOFile(file).delete();
      }
    }
    return null;
  }