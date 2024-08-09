  private void statsInit(int windowSize) {

    // initialize
    Object result = run("STATS_INIT(" + windowSize + ")", variables);
    assertNotNull(result);
    variables.put("stats", result);

    // add some values
    values.stream().forEach(val -> run(format("STATS_ADD (stats, %f)", val), variables));
  }