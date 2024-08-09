public static RxDocumentServiceRequest create(OperationType operation,
            ResourceType resourceType,
            String relativePath,
            Observable<byte[]> content,
            Map<String, String> headers) {
        return new RxDocumentServiceRequest(operation, resourceType, relativePath, content, headers, AuthorizationTokenType.PrimaryMasterKey);
    }