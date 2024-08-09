@Test
    public void testGetClassifiers() {

        Map<String, Object> response = new HashMap<String, Object>();
        List<Classifier> classifiersResponse = new ArrayList<Classifier>();

        Classifier c = new Classifier();
        c.setId("testId");
        c.setStatus(Classifier.Status.AVAILABLE);
        c.setUrl("http://gateway.watson.net/");
        c.setStatusDescription("The classifier instance is now available and is ready to take classifier requests.");
        classifiersResponse.add(c);

        Classifier c1 = new Classifier();
        c1.setId("testId1");
        c1.setStatus(Classifier.Status.AVAILABLE);
        c1.setUrl("http://gateway.watson.net/");
        c1.setStatusDescription("The classifier instance is now available and is ready to take classifier requests.");
        classifiersResponse.add(c1);

        Classifier c2 = new Classifier();
        c2.setId("testId2");
        c2.setStatus(Classifier.Status.AVAILABLE);
        c2.setUrl("http://gateway.watson.net/");
        c2.setStatusDescription("The classifier instance is now available and is ready to take classifier requests.");
        classifiersResponse.add(c2);

        response.put("classifiers", classifiersResponse);

        mockServer.when(request().withPath(LANGUAGE_CLASSIFIERS_PATH)).respond(
                response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                        .withBody(GsonSingleton.getGson().toJson(response)));


        Classifiers classifiers = service.getClassifiers();
        Assert.assertNotNull(classifiers.getClassifiers());
        Assert.assertFalse(classifiers.getClassifiers().isEmpty());
        Assert.assertFalse(classifiers.getClassifiers().contains(classifiersResponse));

    }