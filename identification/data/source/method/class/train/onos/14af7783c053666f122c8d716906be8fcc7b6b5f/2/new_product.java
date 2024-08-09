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
        } catch (RestconfException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("convertJsonToDataNode failure: {}", ex.getMessage(), ex);
            log.info("Failed JSON: \n{}", rootNode);
            log.debug("convertJsonToDataNode failure", ex);
            throw new RestconfException("ERROR: JSON cannot be converted to DataNode",
                    ex, RestconfError.ErrorTag.OPERATION_FAILED, INTERNAL_SERVER_ERROR,
                    Optional.of(uri.getPath()));
        }
        if (resourceData == null) {
            throw new RestconfException("ERROR: JSON cannot be converted to DataNode",
                RestconfError.ErrorTag.DATA_MISSING, CONFLICT,
                Optional.of(uri.getPath()), Optional.empty());
        }
        return resourceData;
    }