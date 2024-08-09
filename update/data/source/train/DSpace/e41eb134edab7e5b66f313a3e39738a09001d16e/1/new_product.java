public EZIDResponse delete(String name)
            throws IOException, IdentifierException, URISyntaxException
    {
        // DELETE path
        HttpDelete request;
        URI uri = new URI(scheme, host, ID_PATH + authority + name, null);
        request = new HttpDelete(uri);
        HttpResponse response = client.execute(request);
        return new EZIDResponse(response);
    }