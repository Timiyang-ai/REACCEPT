public String execute(String url, String payload,
			Map<String, String> headers) throws InvalidResponseDataException,
			IOException, InterruptedException, HttpErrorException,
			ClientActionRequiredException {
		String successResponse = Constants.EMPTY_STRING, errorResponse = Constants.EMPTY_STRING;
		int responsecode = -1;
		BufferedReader reader = null;
		OutputStreamWriter writer = null;
		connection.setRequestProperty("Content-Length", ""
				+ payload.trim().length());
		if (headers != null) {
			log.debug("curl command: ");
			log.debug("curl -v '" + connection.getURL().toString() + "' \\");
			setHttpHeaders(headers);
			Iterator<String> keyIter = headers.keySet().iterator();
			while (keyIter.hasNext()) {
				String key = keyIter.next();
				String value = headers.get(key);
				log.debug("-H \"" + key + ": " + value + "\" \\");
			}
			log.debug("-d '" + payload + "'");
		}
		try {
			// This exception is used to make final log more explicit
			Exception lastException = null;
			int retry = 0;
			retryLoop:
			do {
				try {
					if ("POST".equalsIgnoreCase(connection.getRequestMethod())
							||"PUT".equalsIgnoreCase(connection.getRequestMethod())
							||"PATCH".equalsIgnoreCase(connection.getRequestMethod())) {
						writer = new OutputStreamWriter(
								this.connection.getOutputStream(),
								Charset.forName(Constants.ENCODING_FORMAT));
						writer.write(payload);
						writer.flush();
					}
					responsecode = connection.getResponseCode();
					reader = new BufferedReader(new InputStreamReader(
							connection.getInputStream(),
							Constants.ENCODING_FORMAT));
					if (responsecode >= 200 && responsecode < 300) {
						successResponse = read(reader);
						log.debug("Response : " + successResponse);
						break retryLoop;
					} else {
						successResponse = read(reader);
						throw new ClientActionRequiredException(
								"Response Code : " + responsecode
										+ " with response : " + successResponse);
					}
				} catch (IOException e) {
					lastException = e;
					try {
						responsecode = connection.getResponseCode();
						if (connection.getErrorStream() != null) {
							reader = new BufferedReader(new InputStreamReader(
									connection.getErrorStream(),
									Constants.ENCODING_FORMAT));
							errorResponse = read(reader);
							log.error("Error code : " + responsecode
											+ " with response : " + errorResponse);
						}
						if ((errorResponse == null)
								|| (errorResponse.length() == 0)) {
							errorResponse = e.getMessage();
						}
						if (responsecode <= 500) {
							throw new HttpErrorException(responsecode,
									errorResponse,
									"Error code : "
									+ responsecode + " with response : "
									+ errorResponse, e);
						}
					} catch (HttpErrorException ex) {
						throw ex;
					} catch (Exception ex) {
						lastException = ex;
						log.error("Caught exception while handling error response", ex);
					}
				}
				retry++;
				if (retry > 0) {
					log.error(" Retry  No : " + retry + "...");
					Thread.sleep(this.config.getRetryDelay());
				}
			} while (retry < this.config.getMaxRetry());
			if (successResponse.trim().length() <= 0 && !(responsecode >= 200 && responsecode < 300)) {
				throw new HttpErrorException("retry fails..  check log for more information", lastException);
			}
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			} finally {
				reader = null;
				writer = null;
			}
		}
		return successResponse;
	}