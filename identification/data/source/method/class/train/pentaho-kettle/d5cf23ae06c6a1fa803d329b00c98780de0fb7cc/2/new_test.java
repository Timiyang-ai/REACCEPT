  @Test
  public void findJobTracker_EntryNameIsNull() {
    JobTracker jobTracker = createTracker();
    jobTracker.addJobTracker( createTracker() );

    JobEntryCopy copy = createEntryCopy( null );

    assertNull( jobTracker.findJobTracker( copy ) );
  }