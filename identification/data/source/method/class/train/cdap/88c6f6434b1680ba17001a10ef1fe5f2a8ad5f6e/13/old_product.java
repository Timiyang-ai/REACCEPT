@Override
  public boolean execute(String sql) throws SQLException {
    if (isClosed) {
      throw new SQLException("Can't execute after statement has been closed");
    }
    stmtCompleted = false;
    if (resultSet != null) {
      // As requested by the Statement interface javadoc, "All execution methods in the Statement interface
      // implicitly close a statment's current ResultSet object if an open one exists"
      resultSet.close();
    }

    // TODO in future, the polling logic should be in another SyncExploreClient
    try {
      stmtHandle = exploreClient.execute(sql);
      Status status = ExploreClientUtil.waitForCompletionStatus(exploreClient, stmtHandle, 200,
                                                                TimeUnit.MILLISECONDS, MAX_POLL_TRIES);
      stmtCompleted = true;
      if (status.getStatus() != Status.OpStatus.FINISHED && status.getStatus() != Status.OpStatus.CANCELED) {
        throw new SQLException(String.format("Statement '%s' execution did not finish successfully. " +
                                             "Got final state - %s", sql, status.getStatus().toString()));
      }
      resultSet = new ExploreQueryResultSet(exploreClient, this, stmtHandle);
      return status.hasResults();
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