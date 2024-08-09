@Test
  public void stop() throws IOException {
    final String id = wait(query(_JOBS_SCHEDULE.args(VERY_SLOW_QUERY)));
    query(_JOBS_STOP.args(id));

    // check if query was interrupted
    while(true) {
      try {
        eval(_JOBS_RESULT.args(id));
        fail("Query was not stopped.");
      } catch(final QueryException ex) {
        // query was successfully stopped
        if(ex.error() == JOBS_UNKNOWN_X) break;
        // query is still running: check error code
        assertSame(JOBS_RUNNING_X, ex.error());
      }
    }
  }