public EZIDResponse mint(Map<String, String> metadata)
            throws IOException, IdentifierException, URISyntaxException
    {
        // POST path [+metadata]
        HttpPost request;
        URI uri = new URI(scheme, host, SHOULDER_PATH + authority, null);
        request = new HttpPost(uri);
        if (null != metadata)
        {
            request.setEntity(new StringEntity(formatMetadata(metadata), UTF_8));
        }
        HttpResponse response = client.execute(request);
        EZIDResponse myResponse = new EZIDResponse(response);
        return myResponse;
    }