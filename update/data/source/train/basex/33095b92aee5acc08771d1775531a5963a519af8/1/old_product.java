public static String db(final MetaData meta, final boolean bold, final boolean index,
      final boolean create) {

    final TokenBuilder tb = new TokenBuilder();
    final String header = (bold ?
        new TokenBuilder().bold().add('%').norm().toString() : "%") + NL;
    tb.addExt(header, DB_PROPS);
    info(tb, NAME, meta.name);
    info(tb, SIZE, Performance.format(meta.dbsize()));
    info(tb, NODES, meta.size);

    // count number of raw files
    info(tb, DOCUMENTS, meta.ndocs);
    info(tb, BINARIES, meta.path != null ? meta.binaries().descendants().size() : 0);
    info(tb, TIMESTAMP, DateTime.format(new Date(meta.dbtime())));
    if(meta.corrupt) tb.add(' ' + DB_CORRUPT + NL);

    tb.add(NL).addExt(header, RES_PROPS);
    if(create && !meta.original.isEmpty()) info(tb, INPUT_PATH, meta.original);
    if(meta.filesize != 0) info(tb, INPUT_SIZE, Performance.format(meta.filesize));
    info(tb, TIMESTAMP, DateTime.format(new Date(meta.time)));
    info(tb, ENCODING, meta.encoding);
    info(tb, MainOptions.CHOP.name(), meta.chop);

    if(index) {
      tb.add(NL).addExt(header, INDEXES);
      if(meta.oldindex()) {
        tb.add(' ' + H_INDEX_FORMAT + NL);
      } else {
        info(tb, UP_TO_DATE, meta.uptodate);
        info(tb, MainOptions.TEXTINDEX.name(), meta.textindex);
        info(tb, MainOptions.ATTRINDEX.name(), meta.attrindex);
        info(tb, MainOptions.ATTRTOKENIZE.name(), meta.attrtokeninclude);
        info(tb, MainOptions.FTINDEX.name(), meta.ftindex);
        info(tb, MainOptions.TEXTINCLUDE.name(), meta.textinclude);
        info(tb, MainOptions.ATTRINCLUDE.name(), meta.attrinclude);
        info(tb, MainOptions.FTINCLUDE.name(), meta.ftinclude);
        info(tb, MainOptions.LANGUAGE.name(), meta.language);
        info(tb, MainOptions.STEMMING.name(), meta.stemming);
        info(tb, MainOptions.CASESENS.name(), meta.casesens);
        info(tb, MainOptions.DIACRITICS.name(), meta.diacritics);
        info(tb, MainOptions.STOPWORDS.name(), meta.stopwords);
        info(tb, MainOptions.UPDINDEX.name(), meta.updindex);
        info(tb, MainOptions.AUTOOPTIMIZE.name(), meta.autoopt);
        info(tb, MainOptions.MAXCATS.name(), meta.maxcats);
        info(tb, MainOptions.MAXLEN.name(), meta.maxlen);
        info(tb, MainOptions.INDEXSPLITSIZE.name(), meta.splitsize);
        info(tb, MainOptions.FTINDEXSPLITSIZE.name(), meta.ftsplitsize);
      }
    }
    return tb.toString();
  }