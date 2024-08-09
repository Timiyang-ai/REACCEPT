public Summary getSummary(final File imagesFile) {
    if (imagesFile == null || !imagesFile.exists())
      throw new IllegalArgumentException("imagesFile cannot be null or empty");

    final MediaType mediaType = HttpMediaType.BINARY_FILE;
    final RequestBody body =
        new MultipartBuilder()
            .type(MultipartBuilder.FORM)
            .addFormDataPart(IMAGES_FILE, imagesFile.getName(),
                RequestBody.create(mediaType, imagesFile)).build();

    final RequestBuilder requestBuilder = RequestBuilder.post(SUMMARY_PATH).withBody(body);

    return executeRequest(requestBuilder.build(), Summary.class);

  }