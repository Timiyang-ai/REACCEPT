public DocumentCollection getDocumentCollection(Map<String, Object> docListParams) {
        Request request = Request.Get(DocumentConversion.DOCUMENTS_PATH);

        if(docListParams != null) {
            if (docListParams.get(DocumentConversion.TOKEN) != null) {
                String token = (String) docListParams.get(DocumentConversion.TOKEN);
                if (!token.isEmpty())
                    request.withQuery(DocumentConversion.TOKEN, token);
            }

            if (docListParams.get(DocumentConversion.LIMIT) != null) {
                int limit = ((Integer) docListParams.get(DocumentConversion.LIMIT)).intValue();
                if (limit > 0)
                    request.withQuery(DocumentConversion.LIMIT, limit);
                else
                    request.withQuery(DocumentConversion.LIMIT, DocumentConversion.DEFAULT_LIMIT);
            }

            if (docListParams.get(DocumentConversion.NAME) != null) {
                String name = (String) docListParams.get(DocumentConversion.NAME);
                if (!name.isEmpty())
                    request.withQuery(DocumentConversion.NAME, name);
            }

            if (docListParams.get(DocumentConversion.SINCE) != null) {
                Date since = (Date) docListParams.get(DocumentConversion.SINCE);
                request.withQuery(DocumentConversion.SINCE, ConversionUtils.convertToISO(since));
            }

            if (docListParams.get(DocumentConversion.MEDIA_TYPE) != null) {
                String mediaType = (String) docListParams.get(DocumentConversion.MEDIA_TYPE);
                if (!mediaType.isEmpty())
                    request.withQuery(DocumentConversion.MEDIA_TYPE, mediaType);
            }
        }
        HttpRequestBase requestBase = request.build();
        try {
            HttpResponse response = docConversionService.execute(requestBase);
            String documentCollectionAsJson = ResponseUtil.getString(response);
            DocumentCollection documentCollection = GsonSingleton.getGson().fromJson(
                                                    documentCollectionAsJson, DocumentCollection.class);
            return documentCollection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }