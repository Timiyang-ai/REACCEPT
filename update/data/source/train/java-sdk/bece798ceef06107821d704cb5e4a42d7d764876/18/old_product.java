public Dialog createDialog(final String name, final File dialogFile) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("name can not be null or empty");

		if (dialogFile == null || !dialogFile.exists())
			throw new IllegalArgumentException(
					"dialogFile can not be null or empty");

		try {
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("file", new FileBody(dialogFile));
			reqEntity.addPart("name", new StringBody(name,Charset.forName("UTF-8")));

			HttpRequestBase request = Request.Post("/v1/dialogs")
					.withEntity(reqEntity).build();

			HttpResponse response = execute(request);
			return ResponseUtil.getObject(response, Dialog.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}