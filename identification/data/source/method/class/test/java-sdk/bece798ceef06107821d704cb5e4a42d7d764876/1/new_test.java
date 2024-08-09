@Test
  public void testSynthesize() {
    final File audio = new File("src/test/resources/sample1.wav");
    if (audio == null || !audio.exists() || !audio.isFile())
      throw new IllegalArgumentException("audio is not a valid audio file");

    try {
      final List<Parameter> parameters = new ArrayList<Parameter>();
      parameters.add(new Parameter("text", text));
      parameters.add(new Parameter("voice", Voice.EN_LISA.getName()));
      parameters.add(new Parameter("accept", HttpMediaType.AUDIO_WAV));

      mockServer.when(request().withQueryStringParameters(parameters).withPath(SYNTHESIZE_PATH))
          .respond(
              response().withHeaders(
                  new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.AUDIO_WAV)).withBody(
                  Files.toByteArray(audio)));

      final InputStream in = service.synthesize(text, Voice.EN_LISA, HttpMediaType.AUDIO_WAV);
      Assert.assertNotNull(in);

      writeInputStreamToOutputStream(in, new FileOutputStream("target/output.wav"));

    } catch (final FileNotFoundException e) {
      Assert.fail(e.getMessage());
    } catch (final IOException e) {
      Assert.fail(e.getMessage());
    }
  }