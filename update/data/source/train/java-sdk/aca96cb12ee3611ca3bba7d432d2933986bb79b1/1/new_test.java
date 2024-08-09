@Test
	public void testSynthesize() {
		File audio = new File("src/test/resources/sample1.wav");
		if (audio == null || !audio.exists() || !audio.isFile())
			throw new IllegalArgumentException(
					"audio is not a valid audio file");

		InputStreamEntity reqEntity = null;
		try {
		reqEntity = new InputStreamEntity(new FileInputStream(audio), -1);
			List<Parameter> parameters = new ArrayList<Parameter>();
			parameters.add(new Parameter("text",text));
			parameters.add(new Parameter("voice", Voice.EN_LISA.getName()));
			parameters.add(new Parameter("accept",MediaType.AUDIO_WAV));

			mockServer.when(request().withQueryStringParameters(parameters).withPath(SYNTHESIZE_PATH)).respond(
					response().withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, MediaType.AUDIO_WAV))
							.withBody(IOUtils.toString(reqEntity.getContent())));

		InputStream in = service.synthesize(text, Voice.EN_LISA, MediaType.AUDIO_WAV);
		Assert.assertNotNull(in);

			writeInputStreamToOutputStream(in, new FileOutputStream("target/output.wav"));

		} catch (FileNotFoundException e) {
			Assert.fail(e.getMessage());
		}catch(IOException e)
		{
			Assert.fail(e.getMessage());
		}
	}