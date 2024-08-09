public void updateProfile(final String dialogId, final List<NameValue> nameValues) {
    if (dialogId == null || dialogId.isEmpty())
      throw new IllegalArgumentException("dialogId cannot be null or empty");

    if (nameValues == null || nameValues.isEmpty())
      throw new IllegalArgumentException("nameValues cannot be null or empty");

    final JsonObject contentJson = new JsonObject();

    contentJson.add(NAME_VALUES, GsonSingleton.getGson().toJsonTree(nameValues));

    final Request request =
        RequestBuilder.put("/v1/dialogs/" + dialogId + "/profile").withBodyJson(contentJson)
            .build();
    executeWithoutResponse(request);
  }