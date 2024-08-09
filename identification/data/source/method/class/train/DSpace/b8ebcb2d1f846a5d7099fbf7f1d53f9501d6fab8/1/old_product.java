public EZIDResponse mint(Map<String, String> metadata)
            throws IOException, IdentifierException
    {
        // POST path [+metadata]
        HttpPost request;
        request = new HttpPost(url);
        if (null != metadata)
        {
            request.setEntity(new StringEntity(formatMetadata(metadata), "UTF-8"));
        }
        HttpResponse response = client.execute(request);
        EZIDResponse myResponse = new EZIDResponse(response);
        // TODO add the identifier to the path for subsequent operations?
        return myResponse;
    }