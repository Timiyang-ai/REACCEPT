public void register(final Registration registration) {


        final URI uri = createURI("/service/register");
        HTTP.Response response = HTTP.jsonRestCallViaPUT(uri.toString(), toJson(registration));

        if (response.status() != 200) {
            die("Error registering service with Consul", uri, registration, response.payloadAsString());
        }
    }