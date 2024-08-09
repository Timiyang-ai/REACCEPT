  private List<ScheduledRuntime> getScheduledRunTime(Id.Program program, boolean next) throws Exception {
    String nextRunTimeUrl = String.format("apps/%s/workflows/%s/%sruntime", program.getApplicationId(), program.getId(),
                                          next ? "next" : "previous");
    String versionedUrl = getVersionedAPIPath(nextRunTimeUrl, Constants.Gateway.API_VERSION_3_TOKEN,
                                              program.getNamespaceId());
    HttpResponse response = doGet(versionedUrl);
    return readResponse(response, new TypeToken<List<ScheduledRuntime>>() { }.getType());
  }