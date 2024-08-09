public ServiceCall<List<Voice>> getVoices() {
    final okhttp3.Request request = RequestBuilder.get("/v1/voices").build3();
    return createServiceCall(createCall(request), ResponseUtil.getVoiceListConverter(listVoiceType));
  }