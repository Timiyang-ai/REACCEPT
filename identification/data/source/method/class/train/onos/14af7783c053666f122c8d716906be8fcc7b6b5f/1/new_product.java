public static ResourceData convertJsonToDataNode(URI uri,
                                                     ObjectNode rootNode) {
        RuntimeContext.Builder runtimeContextBuilder = new DefaultRuntimeContext.Builder();
        runtimeContextBuilder.setDataFormat(JSON_FORMAT);
        RuntimeContext context = runtimeContextBuilder.build();
        ResourceData resourceData = null;
        InputStream jsonData = null;
        try {
            if (rootNode != null) {
                jsonData = convertObjectNodeToInputStream(rootNode);
            }
            String uriString = getRawUriPath(uri);

            CompositeStream compositeStream = new DefaultCompositeStream(uriString, jsonData);
            // CompositeStream --- YangRuntimeService ---> CompositeData.
            CompositeData compositeData = YANG_RUNTIME.decode(compositeStream, context);
            resourceData = compositeData.resourceData();
        } catch (Exception ex) {
            log.error("convertJsonToDataNode failure: {}", ex.getMessage());
            log.debug("convertJsonToDataNode failure", ex);
        }
        if (resourceData == null) {
            throw new RestconfException("ERROR: JSON cannot be converted to DataNode",
                                        INTERNAL_SERVER_ERROR);
        }
        return resourceData;
    }