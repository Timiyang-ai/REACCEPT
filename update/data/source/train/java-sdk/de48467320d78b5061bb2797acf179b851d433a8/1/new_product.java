public List<Voice> getVoices() {
    final Request request = RequestBuilder.get("/v1/voices").build();
    final Response response = execute(request);
    final JsonObject jsonObject = ResponseUtil.getJsonObject(response);
    final List<Voice> voices =
        GsonSingleton.getGsonWithoutPrettyPrinting().fromJson(jsonObject.get("voices"), listVoiceType);
    return voices;
  }