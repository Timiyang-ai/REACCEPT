public static InputStream convertObjectNodeToInputStream(ObjectNode rootNode) {
        String json = rootNode.toString();
        InputStream inputStream;
        try {
            inputStream = IOUtils.toInputStream(json);
        } catch (Exception e) {
            throw new RestconfException("ERROR: Json Node failed to parse", e,
                RestconfError.ErrorTag.MALFORMED_MESSAGE, BAD_REQUEST,
                Optional.empty());
        }
        return inputStream;
    }