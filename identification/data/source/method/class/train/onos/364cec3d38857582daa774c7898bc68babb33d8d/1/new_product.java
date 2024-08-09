public static InputStream convertObjectNodeToInputStream(ObjectNode rootNode) {
        String json = rootNode.toString();
        InputStream inputStream;
        try {
            inputStream = IOUtils.toInputStream(json);
        } catch (Exception e) {
            throw new RestconfUtilsException("ERROR: Json Node failed to parse");
        }
        return inputStream;
    }