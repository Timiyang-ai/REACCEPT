@Test
  public void testUpdate() throws Exception {
    List<Metric> metrics = new ArrayList<>(NUM_METRICS);
    for (int i = 0; i < NUM_METRICS; i++) {
      metrics.add(new Metric("test", BasicTagList.EMPTY, System.currentTimeMillis(), VALUE));
    }

    try {
      observer.update(metrics);
    } catch (AmazonClientException e) {
      e.printStackTrace();
    }
  }