private Str contentType(final QueryContext qc) throws QueryException {
    final Data data = checkData(qc);
    final String path = path(1, qc);
    final int pre = data.resources.doc(path);
    if(pre != -1) {
      // check mime type; return application/xml if returned string is not of type xml
      final String mt = MimeTypes.get(string(data.text(pre, true)));
      return Str.get(MimeTypes.isXML(mt) ? mt : MimeTypes.APP_XML);
    }
    if(!data.inMemory()) {
      final IOFile io = data.meta.binary(path);
      if(io.exists() && !io.isDir()) return Str.get(MimeTypes.get(path));
    }
    throw WHICHRES_X.get(info, path);
  }