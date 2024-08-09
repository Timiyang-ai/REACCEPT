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



        response.put("classifiers", classifiersResponse);

        mockServer.when(request().withPath(CLASSIFIERS_PATH)).respond(
                response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                        .withBody(GsonSingleton.getGson().toJson(response)));


        Classifiers classifiers = service.getClassifiers();
        assertNotNull(classifiers.getClassifiers());
        assertFalse(classifiers.getClassifiers().isEmpty());
        assertFalse(classifiers.getClassifiers().contains(classifiersResponse));

    }