public Dialog createDialog(final String name, final File dialogFile) {
    if (name == null || name.isEmpty())
      throw new IllegalArgumentException("name cannot be null or empty");

    if (dialogFile == null || !dialogFile.exists())
      throw new IllegalArgumentException("dialogFile cannot be null or empty");

    final RequestBody body =
        new MultipartBuilder()
            .type(MultipartBuilder.FORM)
            .addFormDataPart(FILE, dialogFile.getName(),
                RequestBody.create(HttpMediaType.BINARY_FILE, dialogFile))
            .addFormDataPart(NAME, name).build();

    final Request request = RequestBuilder.post(PATH_DIALOGS).withBody(body).build();

    return executeRequest(request, Dialog.class);
  }