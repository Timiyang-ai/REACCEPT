private Item updateEntries(final QueryContext ctx) throws QueryException {
    // check argument
    final ANode elm = (ANode) checkType(expr[0].item(ctx, info), NodeType.ELM);
    if(!elm.qname().eq(E_FILE)) ZIP_UNKNOWN.thrw(info, elm.qname());

    // sorted paths in original file
    final String in = attribute(elm, A_HREF, true);

    // target and temporary output file
    final IOFile target = new IOFile(string(checkStr(expr[1], ctx)));
    IOFile out;
    do {
      out = new IOFile(target.path() + new Random().nextInt(0x7FFFFFFF));
    } while(out.exists());

    // open zip file
    if(!new IOFile(in).exists()) ZIP_NOTFOUND.thrw(info, in);
    ZipFile zf = null;
    boolean ok = true;
    try {
      zf = new ZipFile(in);
      // write zip file
      FileOutputStream fos = null;
      try {
        fos = new FileOutputStream(out.path());
        final ZipOutputStream zos =
          new ZipOutputStream(new BufferedOutputStream(fos));
        // fill new zip file with entries from old file and description
        create(zos, elm.children(), "", zf, ctx);
        zos.close();
      } catch(final IOException ex) {
        ok = false;
        ZIP_FAIL.thrw(info, ex.getMessage());
      } finally {
        if(fos != null) try { fos.close(); } catch(final IOException ex) { }
      }
    } catch(final IOException ex) {
      throw ZIP_FAIL.thrw(info, ex.getMessage());
    } finally {
      if(zf != null) try { zf.close(); } catch(final IOException e) { }
      if(ok) {
        // rename temporary file to final target
        target.delete();
        out.rename(target);
      } else {
        // remove temporary file
        out.delete();
      }
    }
    return null;
  }