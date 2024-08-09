private ClientResponse mkdir(long reqId, BinaryRawReader reader) {
        String path = reader.readString();

        return modelStorage.lockPaths(() -> {
            if (modelStorage.exists(path))
                return error(reqId, "Directory already exists [path=" + path + "]");

            modelStorage.mkdir(path);
            return new ClientResponse(reqId);
        }, path);
    }