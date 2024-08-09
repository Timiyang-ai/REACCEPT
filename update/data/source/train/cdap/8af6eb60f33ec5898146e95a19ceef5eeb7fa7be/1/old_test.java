@Test
  public void testDelete() throws Exception {
    // Delete an non-existing app
    HttpResponse response = doDelete(getVersionedAPIPath("apps/XYZ", Constants.Gateway.API_VERSION_3_TOKEN,
                                                         TEST_NAMESPACE1));
    Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    // Start a fow for the App
    deploy(WordCountApp.class, Constants.Gateway.API_VERSION_3_TOKEN, TEST_NAMESPACE1);
    Id.Program program = Id.Program.from(TEST_NAMESPACE1, "WordCountApp", ProgramType.FLOW, "WordCountFlow");
    startProgram(program);
    waitState(program, "RUNNING");
    // Try to delete an App while its flow is running
    response = doDelete(getVersionedAPIPath("apps/WordCountApp", Constants.Gateway.API_VERSION_3_TOKEN,
                                            TEST_NAMESPACE1));
    Assert.assertEquals(409, response.getStatusLine().getStatusCode());
    Assert.assertEquals("'" + program.getApplication() +
                          "' could not be deleted. Reason: The following programs are still running: "
                          + program.getId(), readResponse(response));

    stopProgram(program);
    waitState(program, "STOPPED");

    startProgram(program);
    waitState(program, "RUNNING");
    // Try to delete all Apps while flow is running
    response = doDelete(getVersionedAPIPath("apps", Constants.Gateway.API_VERSION_3_TOKEN,
                                            TEST_NAMESPACE1));
    Assert.assertEquals(409, response.getStatusLine().getStatusCode());
    Assert.assertEquals("'" + program.getNamespace() +
                          "' could not be deleted. Reason: The following programs are still running: "
                          + program.getApplicationId() + ": " + program.getId(), readResponse(response));

    stopProgram(program);
    waitState(program, "STOPPED");

    // Delete the app in the wrong namespace
    response = doDelete(getVersionedAPIPath("apps/WordCountApp", Constants.Gateway.API_VERSION_3_TOKEN,
                                            TEST_NAMESPACE2));
    Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    // Delete an non-existing app with version
    response = doDelete(getVersionedAPIPath("apps/XYZ/versions/" + VERSION1,
                                                         Constants.Gateway.API_VERSION_3_TOKEN, TEST_NAMESPACE1));
    Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    // Deploy an app with version
    Id.Artifact wordCountArtifactId = Id.Artifact.from(Id.Namespace.DEFAULT, "wordcountapp", VERSION1);
    addAppArtifact(wordCountArtifactId, WordCountApp.class);
    AppRequest<? extends Config> wordCountRequest = new AppRequest<>(
      new ArtifactSummary(wordCountArtifactId.getName(), wordCountArtifactId.getVersion().getVersion()));
    ApplicationId wordCountApp1 = NamespaceId.DEFAULT.app("WordCountApp", VERSION1);
    Assert.assertEquals(200, deploy(wordCountApp1, wordCountRequest).getStatusLine().getStatusCode());

    // Start a flow for the App
    ProgramId program1 = wordCountApp1.program(ProgramType.FLOW, "WordCountFlow");
    startProgram(program1, 200);
    waitState(program1, "RUNNING");
    // Try to delete an App while its flow is running
    response = doDelete(getVersionedAPIPath(
      String.format("apps/%s/versions/%s", wordCountApp1.getApplication(), wordCountApp1.getVersion()),
      Constants.Gateway.API_VERSION_3_TOKEN, wordCountApp1.getNamespace()));
    Assert.assertEquals(409, response.getStatusLine().getStatusCode());
    Assert.assertEquals("'" + program1.getParent().toId() + "' could not be deleted. Reason: The following programs" +
                          " are still running: " + program1.getProgram(), readResponse(response));

    stopProgram(program1, null, 200, null);
    waitState(program1, "STOPPED");

    // Delete the app with version in the wrong namespace
    response = doDelete(getVersionedAPIPath(
      String.format("apps/%s/versions/%s", wordCountApp1.getApplication(), wordCountApp1.getVersion()),
      Constants.Gateway.API_VERSION_3_TOKEN, TEST_NAMESPACE2));
    Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    //Delete the app with version after stopping the flow
    response = doDelete(getVersionedAPIPath(
      String.format("apps/%s/versions/%s", wordCountApp1.getApplication(), wordCountApp1.getVersion()),
      Constants.Gateway.API_VERSION_3_TOKEN, wordCountApp1.getNamespace()));
    Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    response = doDelete(getVersionedAPIPath(
      String.format("apps/%s/versions/%s", wordCountApp1.getApplication(), wordCountApp1.getVersion()),
      Constants.Gateway.API_VERSION_3_TOKEN, wordCountApp1.getNamespace()));
    Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    //Delete the App after stopping the flow
    response = doDelete(getVersionedAPIPath("apps/WordCountApp/", Constants.Gateway.API_VERSION_3_TOKEN,
                                            TEST_NAMESPACE1));
    Assert.assertEquals(200, response.getStatusLine().getStatusCode());
    response = doDelete(getVersionedAPIPath("apps/WordCountApp/", Constants.Gateway.API_VERSION_3_TOKEN,
                                            TEST_NAMESPACE1));
    Assert.assertEquals(404, response.getStatusLine().getStatusCode());

    // deleting the app should not delete the artifact
    response = doGet(getVersionedAPIPath("artifacts/WordCountApp", Constants.Gateway.API_VERSION_3_TOKEN,
                                         TEST_NAMESPACE1));
    Assert.assertEquals(200, response.getStatusLine().getStatusCode());

    List<ArtifactSummary> summaries = readResponse(response, new TypeToken<List<ArtifactSummary>>() {
    }.getType());
    Assert.assertFalse(summaries.isEmpty());
  }