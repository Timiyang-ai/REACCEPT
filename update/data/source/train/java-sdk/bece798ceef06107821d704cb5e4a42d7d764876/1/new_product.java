public List<ConversationData> getConversationData(final Map<String, Object> params) {
    final String dialogId = (String) params.get(DIALOG_ID);

    final Date from = (Date) params.get(DATE_FROM);
    final Date to = (Date) params.get(DATE_TO);

    final Integer offset = (Integer) params.get(OFFSET);
    final Integer limit = (Integer) params.get(LIMIT);

    if (dialogId == null || dialogId.isEmpty())
      throw new IllegalArgumentException(DIALOG_ID + " cannot be null or empty");

    if (from == null)
      throw new IllegalArgumentException(DATE_FROM + " cannot be null");

    if (to == null)
      throw new IllegalArgumentException(DATE_TO + " cannot be null");

    if (from.after(to))
      throw new IllegalArgumentException("'" + DATE_FROM + "' is greater than '" + DATE_TO + "'");

    final String fromString = sdfDate.format(from);
    final String toString = sdfDate.format(to);

    final String path = String.format("/v1/dialogs/%s/conversation", dialogId);

    final RequestBuilder requestBuilder =
        RequestBuilder.get(path).withQuery(DATE_FROM, fromString, DATE_TO, toString);

    if (offset != null)
      requestBuilder.withQuery(OFFSET, offset);
    if (limit != null)
      requestBuilder.withQuery(LIMIT, limit);

    final Request request = requestBuilder.build();

    final Response response = execute(request);
    final JsonObject jsonObject = ResponseUtil.getJsonObject(response);
    final List<ConversationData> conversationDataList =
        GsonSingleton.getGson().fromJson(jsonObject.get("conversations"), listConversationDataType);
    return conversationDataList;
  }