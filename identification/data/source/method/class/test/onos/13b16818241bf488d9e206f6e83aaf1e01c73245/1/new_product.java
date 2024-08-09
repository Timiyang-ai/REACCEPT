public static ObjectNode convertInputStreamToObjectNode(InputStream inputStream) {
        ObjectNode rootNode;
        ObjectMapper mapper = new ObjectMapper();
        try {
            rootNode = readTreeFromStream(mapper, inputStream);
        } catch (IOException e) {
            throw new RestconfException("ERROR: InputStream failed to parse",
                    e, RestconfError.ErrorTag.OPERATION_FAILED, INTERNAL_SERVER_ERROR,
                    Optional.empty());
        }
        return rootNode;
    }