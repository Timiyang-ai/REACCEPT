static void create(final int... db) throws BaseXException {
    for(final int d : db) {
      final String[] doc = DOCS[d - 1];
      new CreateDB(doc[0], doc[1]).execute(context);
    }
  }