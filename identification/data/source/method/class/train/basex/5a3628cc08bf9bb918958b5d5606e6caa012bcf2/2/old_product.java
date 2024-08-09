List<WebDAVResource> list(final String db, final String path) throws IOException {
    final WebDAVQuery query = new WebDAVQuery(STRING_JOIN.args(
      _DB_LIST_DETAILS.args("$db", "$path") + " ! (" +
      "@raw,@content-type,@modified-date,@size," + SUBSTRING_AFTER.args("text()", "$path") + ')',
      "out:tab()"));
    query.bind("db", db);
    query.bind("path", path);
    final String[] result = results(query);

    final HashSet<String> paths = new HashSet<>();
    final List<WebDAVResource> ch = new ArrayList<>();
    final int rs = result.length;
    for(int r = 0; r < rs; r += 5) {
      final boolean raw = Boolean.parseBoolean(result[r]);
      final MediaType ctype = new MediaType(result[r + 1]);
      final long mod = DateTime.parse(result[r + 2]).getTime();
      final Long size = raw ? Long.valueOf(result[r + 3]) : null;
      final String pth = stripLeadingSlash(result[r + 4]);
      final int ix = pth.indexOf(SEP);
      // check if document or folder
      if(ix < 0) {
        if(!pth.equals(DUMMY)) ch.add(WebDAVFactory.file(this,
          new WebDAVMetaData(db, path + SEP + pth, mod, raw, ctype, size)));
      } else {
        final String dir = path + SEP + pth.substring(0, ix);
        if(paths.add(dir)) ch.add(WebDAVFactory.folder(this, new WebDAVMetaData(db, dir, mod)));
      }
    }
    return ch;
  }