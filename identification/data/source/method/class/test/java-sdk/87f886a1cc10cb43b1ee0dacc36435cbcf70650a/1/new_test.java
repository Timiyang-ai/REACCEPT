@Test
    public void testClassify() {

        Classification response = new Classification();
        response.setId("testId");
        response.setText("is it sunny?");
        response.setUrl("http://www.ibm.com");
        response.setTopClass("class2");

        List<ClassifiedClass> classes = new ArrayList<ClassifiedClass>();
        ClassifiedClass c1 = new ClassifiedClass();
        c1.setConfidence(0.98189);
        c1.setName("class1");

        ClassifiedClass c2 = new ClassifiedClass();
        c2.setConfidence(0.98188);
        c2.setName("class2");
        classes.add(c1);
        classes.add(c2);

        response.setClasses(classes);
        System.out.println(GsonSingleton.getGson().toJson(response));

        StringBuilder text = new StringBuilder().append("is it sunny?");

        JsonObject contentJson = new JsonObject();
        contentJson.addProperty("text", text.toString());
        String path = String
                .format(LANGUAGE_CLASSIFY_PATH, classifierId);

        mockServer.when(
                request().withMethod("POST").withPath(path)
                        .withBody(contentJson.toString())

        )
                .respond(
                        response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                                .withBody(GsonSingleton.getGson().toJson(response)));

        Classification c = service.classify(classifierId, text.toString());

        Assert.assertNotNull(c);
        Assert.assertEquals(c, response);

    }