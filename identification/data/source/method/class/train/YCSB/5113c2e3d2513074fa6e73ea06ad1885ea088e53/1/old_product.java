@Override
  public int read(String table, String key, Set<String> fields,
      HashMap<String, ByteIterator> result) {

    try {
      Statement stmt;
      Select.Builder selectBuilder;

      if (fields == null) {
        selectBuilder = QueryBuilder.select().all();
      } else {
        selectBuilder = QueryBuilder.select();
        for (String col : fields) {
          ((Select.Selection) selectBuilder).column(col);
        }
      }

      stmt = selectBuilder.from(table).where(QueryBuilder.eq(YCSB_KEY, key))
          .limit(1);
      stmt.setConsistencyLevel(readConsistencyLevel);

      if (debug) {
        System.out.println(stmt.toString());
      }

      ResultSet rs = session.execute(stmt);

      // Should be only 1 row
      if (!rs.isExhausted()) {
        Row row = rs.one();
        ColumnDefinitions cd = row.getColumnDefinitions();

        for (ColumnDefinitions.Definition def : cd) {
          ByteBuffer val = row.getBytesUnsafe(def.getName());
          if (val != null) {
            result.put(def.getName(), new ByteArrayByteIterator(val.array()));
          } else {
            result.put(def.getName(), null);
          }
        }

      }

      return StatusCode.OK;

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error reading key: " + key);
      return StatusCode.ERROR;
    }

  }