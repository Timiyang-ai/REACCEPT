  @Test(expected = NullPointerException.class)
  public void fromCloudTraceContext_Null() {
    AppEngineCloudTraceContextUtils.toCloudTraceContext(null);
  }