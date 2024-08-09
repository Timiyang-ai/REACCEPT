static void createDB() {
    execute(new CreateDB(COLL, DOCPATH + DOC1));
    execute(new Close());
  }