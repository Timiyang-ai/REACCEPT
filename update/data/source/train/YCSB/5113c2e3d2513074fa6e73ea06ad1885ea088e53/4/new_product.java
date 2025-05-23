@Override
  public Status delete(String table, String key) {

    try {
      Statement stmt;

      stmt = QueryBuilder.delete().from(table)
          .where(QueryBuilder.eq(YCSB_KEY, key));
      stmt.setConsistencyLevel(writeConsistencyLevel);

      if (debug) {
        System.out.println(stmt.toString());
      }

      session.execute(stmt);

      return Status.OK;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error deleting key: " + key);
    }

    return Status.ERROR;
  }