@Test
	public void testGetIdentifiableLanguages() {

		Map<String, Object> response = new HashMap<String, Object>();
		List<IdentifiableLanguage> langs = new ArrayList<IdentifiableLanguage>();
		langs.add(new IdentifiableLanguage("af", "Afrikaans"));
		langs.add(new IdentifiableLanguage("ar", "Arabic"));
		langs.add(new IdentifiableLanguage("az", "Azerbaijani"));
		langs.add(new IdentifiableLanguage("zh", "Chinese"));
		response.put("languages", langs);

		mockServer.when(request().withPath(IDENTIFIABLE_LANGUAGES_PATH)).respond(
				response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.APPLICATION_JSON))
						.withBody(GsonSingleton.getGson().toJson(response)));

		List<IdentifiableLanguage> languages = service.getIdentifiableLanguages();

		mockServer.verify(request().withPath(IDENTIFIABLE_LANGUAGES_PATH), VerificationTimes.exactly(1));
		assertNotNull(languages);
		assertEquals(languages,langs);
	}