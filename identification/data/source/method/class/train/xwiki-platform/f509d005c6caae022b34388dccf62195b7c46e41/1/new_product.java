protected String buildURL(AttachmentReference attachmentReference, String fileName)
    {
        // We need the absolute URL because the gallery macro, which is used when viewing an office presentation, uses
        // the syntax of the target document to parse its content and XWiki 2.0 syntax (unlike XWiki 2.1) doesn't
        // support path image references (e.g. image:path:/one/two/three.png).
        String prefix =
            documentAccessBridge.getDocumentURL(attachmentReference.getDocumentReference(), "temp", null, null, true);
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