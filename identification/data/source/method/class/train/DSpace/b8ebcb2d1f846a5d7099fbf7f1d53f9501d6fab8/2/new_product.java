public EZIDResponse lookup(String name)
            throws IdentifierException, IOException, URISyntaxException
    {
        // GET path
        HttpGet request;
        URI uri = new URI(scheme, host, ID_PATH + authority + name, null);
        request = new HttpGet(uri);
        HttpResponse response = client.execute(request);
        return new EZIDResponse(response);
    }