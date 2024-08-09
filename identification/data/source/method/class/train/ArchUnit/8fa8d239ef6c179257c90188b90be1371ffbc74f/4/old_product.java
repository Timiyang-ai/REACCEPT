Location append(String relativeURI) {
        if (uri.toString().endsWith("/") && relativeURI.startsWith("/")) {
            relativeURI = relativeURI.substring(1);
        }
        if (!uri.toString().endsWith("/") && !relativeURI.startsWith("/")) {
            relativeURI = "/" + relativeURI;
        }
        return Location.of(URI.create(uri + relativeURI));
    }