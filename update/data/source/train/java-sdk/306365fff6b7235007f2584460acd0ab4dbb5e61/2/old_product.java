public Document uploadDocument(final File document, final String mediaType) {
        if (mediaType == null || mediaType.isEmpty())
            throw new IllegalArgumentException("media type cannot be null or empty");
        if(!ConversionUtils.isValidMediaType(mediaType))
            throw new IllegalArgumentException("file with the given media type is not supported");
        if (document == null || !document.exists())
            throw new IllegalArgumentException("document cannot be null and must exist");
        try {
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("file", new FileBody(document, mediaType));
            HttpRequestBase request = Request.Post("/v1/documents")
                                             .withEntity(reqEntity).build();

            HttpResponse response = docConversionService.execute(request);
            String documentAsJson = ResponseUtil.getString(response);
            Document doc = GsonSingleton.getGson().fromJson(documentAsJson, Document.class);
            return doc;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }