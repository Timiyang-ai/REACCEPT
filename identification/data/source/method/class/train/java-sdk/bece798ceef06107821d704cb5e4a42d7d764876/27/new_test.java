@Test
  public void testClassify() {

    final Classification response = new Classification();
    response.setId("testId");
    response.setText("is it sunny?");
    response.setUrl("http://www.ibm.com");
    response.setTopClass("class2");

    final List<ClassifiedClass> classes = new ArrayList<ClassifiedClass>();
    final ClassifiedClass c1 = new ClassifiedClass();
    c1.setConfidence(0.98189);
    c1.setName("class1");

    final ClassifiedClass c2 = new ClassifiedClass();
    c2.setConfidence(0.98188);
    c2.setName("class2");
    classes.add(c1);
    classes.add(c2);

    response.setClasses(classes);

    final StringBuilder text = new StringBuilder().append("is it sunny?");

    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("text", text.toString());
    final String path = String.format(CLASSIFY_PATH, classifierId);

    mockServer.when(request().withMethod("POST").withPath(path).withBody(contentJson.toString())

    ).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(response)));

    final Classification c = service.classify(classifierId, text.toString());

    assertNotNull(service.toString());
    assertNotNull(c);
    assertEquals(c, response);

  }