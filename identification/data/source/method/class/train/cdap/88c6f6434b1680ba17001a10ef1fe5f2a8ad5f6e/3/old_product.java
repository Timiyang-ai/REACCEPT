@Override
  public boolean execute(String sql) throws SQLException {
    if (isClosed) {
      throw new SQLException("Can't execute after statement has been closed");
    }

    // TODO in future, the polling logic should be in another SyncExploreClient
    try {
      try {
        clientLock.lock();
        stmtHandle = exploreClient.execute(sql);
      } finally {
        clientLock.unlock();
      }
      // We don't care about passing the lock for getting status
      Status status = ExploreClientUtil.waitForCompletionStatus(exploreClient, stmtHandle, 200,
                                                                TimeUnit.MILLISECONDS, MAX_POLL_TRIES);

      if (status.getStatus() != Status.OpStatus.FINISHED && status.getStatus() != Status.OpStatus.CANCELED) {
        throw new SQLException(String.format("Statement '%s' execution did not finish successfully. " +
                                             "Got final state - %s", sql, status.getStatus().toString()));
      }
      resultSet = new ExploreQueryResultSet(exploreClient, this, stmtHandle);
      return status.hasResults();
    } catch (HandleNotFoundException e) {
      // Cannot happen unless explore server restarted.
      LOG.error("Error running enable explore", e);
      throw Throwables.propagate(e);
    } catch (InterruptedException e) {
      LOG.error("Caught exception", e);
      Thread.currentThread().interrupt();
      // TODO is this the correct behavior?
      return false;
    } catch (ExploreException e) {
      LOG.error("Caught exception", e);
      throw new SQLException(e);
    }
  }