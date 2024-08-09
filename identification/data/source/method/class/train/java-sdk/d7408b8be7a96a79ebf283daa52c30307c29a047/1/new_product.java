public SpeechModel getModel(final String name) {
    if (name == null)
      throw new IllegalArgumentException("name was not specified");

    final Request request = RequestBuilder.get(String.format(PATH_MODEL, name)).build();
    return executeRequest(request, SpeechModel.class);
  }