public void close() {
    for(int i = 0; i < conns.size(); i++) {
      final int key = conns.key(i);
      final Object obj = conns.get(key);
      if(obj == null) continue;
      try {
        if(obj instanceof Connection) ((Connection) obj).close();
        else ((PreparedStatement) obj).close();
      } catch(final SQLException ex) {
        Util.debug(ex);
      }
    }
  }