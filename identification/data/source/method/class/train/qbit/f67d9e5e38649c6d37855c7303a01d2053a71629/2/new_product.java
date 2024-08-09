public void deregister(final String serviceId) {

        final URI uri = createURI("/service/deregister/" + serviceId);

        HTTP.Response response = HTTP.getResponse(uri.toString());

        if (response.status() != 200) {
            die("Error removing registration of service with Consul",
                    uri, serviceId, response.status(), response.payloadAsString());
        }

    }