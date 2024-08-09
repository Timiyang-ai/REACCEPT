static void createDB() throws BaseXException {
    new CreateDB(COLL, DOCPATH + DOC1).execute(context);
    new Close().execute(context);
  }