@Test
	public void testGetModel() {
		// Mock response
		TranslationModel tm = new TranslationModel();
		String model_id = "not-a-real-model";
		tm.setModelId(model_id);
		tm.setSource("en");
		tm.setBaseModelId("en-es");
		tm.setDomain("news");
		tm.setCustomizable(false);
		tm.setDefaultModel(false);
		tm.setOwner("not-a-real-user");
		tm.setStatus("error");
		tm.setName("custom-english-to-spanish");

		mockServer.when(request().withPath(GET_MODELS_PATH + "/" + model_id)).respond(
				response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.APPLICATION_JSON))
						.withBody(GsonSingleton.getGson().toJson(tm)));

		TranslationModel model = service.getModel("not-a-real-model");

		mockServer.verify(request().withPath(GET_MODELS_PATH + "/" + model_id), VerificationTimes.exactly(1));
		assertNotNull(model);
	}