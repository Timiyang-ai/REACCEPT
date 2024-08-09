private HttpResponse grabLog(HttpRequest request, ApplicationId applicationId, Tenant tenant) {
        if (getBindingMatch(request).groupCount() != 7)
            throw new NotFoundException("Illegal POST log request '" + request.getUri() +
                    "': Must have 6 arguments but had " + ( getBindingMatch(request).groupCount()-1 ) );
        final String response = applicationRepository.grabLog(tenant, applicationId);
        return new HttpResponse(200) {
            @Override
            public void render(OutputStream outputStream) throws IOException {
                outputStream.write(response.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public String getContentType() {
                return HttpConfigResponse.JSON_CONTENT_TYPE;
            }
        };
    }