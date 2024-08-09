@Test
	public void testGetVoices() {

		Map<String, Object> response = new HashMap<String, Object>();
		List<Voice> voices = new ArrayList<Voice>();
		Voice voice = new Voice();
		voice.setUrl("http://ibm.watson.com/text-to-speech/voices/en-US_TestMaleVoice");
		voice.setName("en-US_TestMaleVoice");
		voice.setGender("male");
		voice.setLanguage("en-US");
		voice.setDescription("TestMale");

		Voice voice1 = new Voice();
		voice1.setUrl("http://ibm.watson.com/text-to-speech/voices/en-US_TestFemaleVoice");
		voice1.setName("en-US_TestFemaleVoice");
		voice1.setGender("female");
		voice1.setLanguage("en-US");
		voice1.setDescription("TestFemale");

		voices.add(voice);
		voices.add(voice1);

		response.put("voices",voices);

		mockServer.when(request().withPath(GET_VOICES_PATH)).respond(
				response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.APPLICATION_JSON))
						.withBody(GsonSingleton.getGson().toJson(response)));

		List<Voice> result = service.getVoices();
		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
		Assert.assertEquals(result, voices);
	}