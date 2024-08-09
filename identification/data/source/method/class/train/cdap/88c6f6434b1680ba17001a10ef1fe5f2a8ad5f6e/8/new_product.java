@Override
  public boolean execute(String sql) throws SQLException {
    if (isClosed) {
      throw new SQLException("Can't execute after statement has been closed");
    }
    if (resultSet != null) {
      // As requested by the Statement interface javadoc, "All execution methods in the Statement interface
      // implicitly close a statement's current ResultSet object if an open one exists"
      resultSet.close();
      resultSet = null;
    }

    futureResults = exploreClient.submit(namespace, sql);
    try {
      resultSet = new ExploreResultSet(futureResults.get(), this, maxRows);
      // NOTE: Javadoc states: "returns false if the first result is an update count or there is no result"
      // Here we have a result, it may contain rows or may be empty, but it exists.
      return true;
    } catch (InterruptedException e) {
      LOG.error("Caught exception", e);
      Thread.currentThread().interrupt();
      return false;
    } catch (ExecutionException e) {
      Throwable t = Throwables.getRootCause(e);
      if (t instanceof HandleNotFoundException) {
        LOG.error("Error executing query", e);
        throw new SQLException("Unknown state");
      }
      LOG.error("Caught exception", e);
      throw new SQLException(Throwables.getRootCause(e));
    } catch (CancellationException e) {
      // If futureResults has been cancelled
      return false;
    }
  }