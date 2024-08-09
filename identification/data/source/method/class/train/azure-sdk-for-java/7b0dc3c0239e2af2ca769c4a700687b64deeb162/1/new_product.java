public static RxDocumentServiceRequest create(OperationType operation,
            ResourceType resourceType,
            String relativePath,
            Flux<byte[]> content,
            Map<String, String> headers) {
        return new RxDocumentServiceRequest(operation, resourceType, relativePath, content, headers, AuthorizationTokenType.PrimaryMasterKey);
    }