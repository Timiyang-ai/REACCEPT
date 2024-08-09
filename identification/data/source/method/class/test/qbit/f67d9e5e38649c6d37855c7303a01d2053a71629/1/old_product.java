public void register(final Registration registration) {
        final String path = rootPath + "/service/register";
        final HttpResponse httpResponse = httpClient.putJson(path, toJson(registration));
        if (httpResponse.code() != 200) {
            die("Error registering service with Consul", path, registration, httpResponse.body());
        }
    }