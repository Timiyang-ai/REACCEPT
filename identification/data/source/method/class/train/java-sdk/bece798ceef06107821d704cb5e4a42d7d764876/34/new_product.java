public List<Dialog> getDialogs() {
    final Request request = RequestBuilder.get(PATH_DIALOGS).build();

    final Response response = execute(request);
    final JsonObject jsonObject = ResponseUtil.getJsonObject(response);
    final List<Dialog> dialogs =
        GsonSingleton.getGson().fromJson(jsonObject.get("dialogs"), listDialogType);
    return dialogs;
  }