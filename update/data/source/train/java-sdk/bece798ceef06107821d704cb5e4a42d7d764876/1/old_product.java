public List<ConversationData> getConversationData(final Map<String, Object> params) {
		final String dialogId = (String) params.get(DIALOG_ID);
		
		final Date from = (Date) params.get(DATE_FROM);
		final Date to   = (Date) params.get(DATE_TO);
		
		final Integer offset = (Integer) params.get(OFFSET);
		final Integer limit  = (Integer) params.get(LIMIT);
		
		if (dialogId == null || dialogId.isEmpty())
			throw new IllegalArgumentException(DIALOG_ID + " can not be null or empty");

		if (from == null)
			throw new IllegalArgumentException(DATE_FROM + " can not be null");

		if (to == null)
			throw new IllegalArgumentException(DATE_TO + " can not be null");

		if (from.after(to))
			throw new IllegalArgumentException("'"+DATE_FROM+"' is greater than '"+DATE_TO+"'");

		String fromString = sdfDate.format(from);
		String toString = sdfDate.format(to);

		String path = String.format("/v1/dialogs/%s/conversation", dialogId);

		Request requestBuilder = Request.Get(path).withQuery(DATE_FROM,
				fromString, DATE_TO, toString);

		if (offset != null)
			requestBuilder.withQuery(OFFSET, offset);
		if (limit != null)
			requestBuilder.withQuery(LIMIT, limit);

		HttpRequestBase request = requestBuilder.build();

		try {
			HttpResponse response = execute(request);
			JsonObject jsonObject = ResponseUtil.getJsonObject(response);
			List<ConversationData> conversationDataList = GsonSingleton.getGson().fromJson(
					jsonObject.get("conversations"), listConversationDataType);
			return conversationDataList;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}