private ClientResponse mkdir(long reqId, BinaryRawReader reader) {
        String path = reader.readString();
        boolean onlyIfNotExists = reader.readBoolean();

        return modelStorage.lockPaths(() -> {
            if (onlyIfNotExists && modelStorage.exists(path))
                return error(reqId, "Directory already exists [path=" + path + "]");

            modelStorage.mkdir(path, onlyIfNotExists);
            return new ClientResponse(reqId);
        }, path);
    }