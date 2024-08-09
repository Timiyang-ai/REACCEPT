@Override
  public boolean execute(String sql) throws SQLException {
    if (isClosed) {
      throw new SQLException("Can't execute after statement has been closed");
    }
    stmtCompleted = false;
    if (resultSet != null) {
      // As requested by the Statement interface javadoc, "All execution methods in the Statement interface
      // implicitly close a statement's current ResultSet object if an open one exists"
      resultSet.close();
      resultSet = null;
    }

    // TODO in future, the polling logic should be in another SyncExploreClient
    try {
      stmtHandle = exploreClient.execute(sql);
      Status status = ExploreClientUtil.waitForCompletionStatus(exploreClient, stmtHandle, 200,
                                                                TimeUnit.MILLISECONDS, MAX_POLL_TRIES);
      stmtCompleted = true;
      switch (status.getStatus()) {
        case FINISHED:
          resultSet = new ExploreResultSet(exploreClient, this, stmtHandle);
          // NOTE: Javadoc states: "returns false if the first result is an update count or there is no result"
          // Here we have a result, it may contain rows or may be empty, but it exists.
          return true;
        case CANCELED:
          return false;
        default:
          // Any other state can be considered as a "database" access error
          throw new SQLException(String.format("Statement '%s' execution did not finish successfully. " +
                                               "Got final state - %s", sql, status.getStatus().toString()));
      }
    } catch (HandleNotFoundException e) {
      // Cannot happen unless explore server restarted.
      LOG.error("Error executing query", e);
      throw new SQLException("Unknown state");
    } catch (InterruptedException e) {
      LOG.error("Caught exception", e);
      Thread.currentThread().interrupt();
      return false;
    } catch (ExploreException e) {
      LOG.error("Caught exception", e);
      throw new SQLException(e);
    }
  }