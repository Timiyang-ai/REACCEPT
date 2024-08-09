public static ObjectNode convertInputStreamToObjectNode(InputStream inputStream) {
        ObjectNode rootNode;
        ObjectMapper mapper = new ObjectMapper();
        try {
            rootNode = readTreeFromStream(mapper, inputStream);
        } catch (IOException e) {
            throw new RestconfUtilsException("ERROR: InputStream failed to parse");
        }
        return rootNode;
    }