@Override
  public int delete(String table, String key) {

    try {
      Statement stmt;

      stmt = QueryBuilder.delete().from(table)
          .where(QueryBuilder.eq(YCSB_KEY, key));
      stmt.setConsistencyLevel(writeConsistencyLevel);

      if (debug) {
        System.out.println(stmt.toString());
      }

      ResultSet rs = session.execute(stmt);

      return StatusCode.OK;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error deleting key: " + key);
    }

    return StatusCode.ERROR;
  }