public void deregister(final String serviceId) {
        final String path = rootPath + "/service/deregister/" + serviceId;
        final HttpResponse httpResponse = httpClient.get(path);

        if (httpResponse.code() != 200) {
            die("Error removing registration of service with Consul",
                    path, serviceId, httpResponse.code(), httpResponse.body());
        }

    }