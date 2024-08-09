public void updateProfile(final String dialogId, final Integer clientId,
      final Map<String, String> profile) {
    if (dialogId == null || dialogId.isEmpty())
      throw new IllegalArgumentException("dialogId cannot be null or empty");

    if (profile == null || profile.isEmpty())
      throw new IllegalArgumentException("profile cannot be null or empty");

    final JsonObject contentJson = new JsonObject();
    if (clientId != null)
      contentJson.addProperty(CLIENT_ID, clientId);

    contentJson.add(NAME_VALUES, GsonSingleton.getGson().toJsonTree(toNameValue(profile)));

    final Request request =
        RequestBuilder.put(String.format(PATH_PROFILE, dialogId)).withBodyJson(contentJson).build();
    executeWithoutResponse(request);
  }