private Item zipFile(final QueryContext qc) throws QueryException {
    // check argument
    final ANode elm = toElem(exprs[0], qc);
    if(!elm.qname().eq(Q_FILE)) throw ZIP_UNKNOWN_X.get(info, elm.qname());
    // get file
    final String file = attribute(elm, HREF, true);

    // write zip file
    boolean ok = true;
    try(final FileOutputStream fos = new FileOutputStream(file);
        final ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(fos))) {
      create(zos, elm.children(), "", null, qc);
    } catch(final IOException ex) {
      ok = false;
      throw ZIP_FAIL_X.get(info, ex);
    } finally {
      if(!ok) new IOFile(file).delete();
    }
    return null;
  }