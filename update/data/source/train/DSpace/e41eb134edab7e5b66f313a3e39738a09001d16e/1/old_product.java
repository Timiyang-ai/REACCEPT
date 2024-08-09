public EZIDResponse delete()
            throws IOException, IdentifierException
    {
        // DELETE path
        HttpDelete request;
        request = new HttpDelete(url);
        HttpResponse response = client.execute(request);
        return new EZIDResponse(response);
    }