public EZIDResponse lookup()
            throws IdentifierException, IOException
    {
        // GET path
        HttpGet request;
        request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        return new EZIDResponse(response);
    }