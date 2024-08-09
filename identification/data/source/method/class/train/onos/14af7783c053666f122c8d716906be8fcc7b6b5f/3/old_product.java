public static ResourceData convertJsonToDataNode(String uri,
                                                    ObjectNode rootNode) {
        RuntimeContext.Builder runtimeContextBuilder = null;
        runtimeContextBuilder.setDataFormat(JSON_FORMAT);
        RuntimeContext context = runtimeContextBuilder.build();
        InputStream jsonData = null;
        if (rootNode != null) {
            jsonData = convertObjectNodeToInputStream(rootNode);
        }
        CompositeStream compositeStream = new DefaultCompositeStream(uri, jsonData);
        // CompositeStream --- YangRuntimeService ---> CompositeData.
        CompositeData compositeData = YANG_RUNTIME.decode(compositeStream, context);
        ResourceData resourceData = compositeData.resourceData();
        return resourceData;
    }