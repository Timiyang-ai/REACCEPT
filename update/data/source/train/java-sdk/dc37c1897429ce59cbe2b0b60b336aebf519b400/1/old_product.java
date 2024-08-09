public List<Scorecard> getScorecards() {
    Response response = execute(RequestBuilder.get(PATH_SCORECARD).build());
    final JsonObject jsonObject = ResponseUtil.getJsonObject(response);
    final List<Scorecard> scorecards =
        GsonSingleton.getGson().fromJson(jsonObject.get(SCORECARDS), scorecardListType);

    return scorecards;
  }