public String execute(String url, String payload,
			Map<String, String> headers) throws InvalidResponseDataException,
			IOException, InterruptedException, HttpErrorException {

		BufferedReader reader;
		String successResponse;
		InputStream result = executeWithStream(url, payload, headers);
		reader = new BufferedReader(new InputStreamReader(result,
				Constants.ENCODING_FORMAT));
		successResponse = read(reader);

		return successResponse;
	}