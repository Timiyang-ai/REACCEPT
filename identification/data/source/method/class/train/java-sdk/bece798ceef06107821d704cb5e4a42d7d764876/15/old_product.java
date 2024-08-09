public Dilemma dilemmas(final Problem problem,final Boolean generateVisualization) {
		if (problem == null)
			throw new IllegalArgumentException("problem was not specified");
	
		String contentJson = GsonSingleton.getGson().toJson(problem);
		
		Request request = Request.Post("/v1/dilemmas")
				.withContent(contentJson, MediaType.APPLICATION_JSON);
	
		if (generateVisualization != null)
			request.withQuery(GENERATE_VISUALIZATION,generateVisualization);
		
		try {
			HttpResponse response = execute(request.build());
			String dilemmaJson = ResponseUtil.getString(response);

			Dilemma dilemma = GsonSingleton.getGson().fromJson(dilemmaJson, Dilemma.class);
			return dilemma;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}