protected String buildURL(AttachmentReference attachmentReference, String fileName)
    {
        String prefix =
            documentAccessBridge.getDocumentURL(attachmentReference.getDocumentReference(), "temp", null, null);
        try {
            String encodedAttachmentName = URLEncoder.encode(attachmentReference.getName(), DEFAULT_ENCODING);
            String encodedFileName = URLEncoder.encode(fileName, DEFAULT_ENCODING);
            return String.format("%s/%s/%s/%s", prefix, MODULE_NAME, encodedAttachmentName, encodedFileName);
        } catch (UnsupportedEncodingException e) {
            // This should never happen.
            getLogger().error("Failed to encode URL using " + DEFAULT_ENCODING, e);
            return null;
        }
    }