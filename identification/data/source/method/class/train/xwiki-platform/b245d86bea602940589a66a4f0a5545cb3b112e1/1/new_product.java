protected File getTemporaryDirectory(AttachmentReference attachmentReference) throws Exception
    {
        // Extract wiki, space, page and attachment name.
        String wiki = attachmentReference.getDocumentReference().getWikiReference().getName();
        String space = attachmentReference.getDocumentReference().getParent().getName();
        String page = attachmentReference.getDocumentReference().getName();
        String attachmentName = attachmentReference.getName();

        // Encode to avoid illegal characters in file paths.
        wiki = URLEncoder.encode(wiki, DEFAULT_ENCODING);
        space = URLEncoder.encode(space, DEFAULT_ENCODING);
        page = URLEncoder.encode(page, DEFAULT_ENCODING);
        attachmentName = URLEncoder.encode(attachmentName, DEFAULT_ENCODING);

        // Create temporary directory.
        String path = String.format("temp/%s/%s/%s/%s/%s/", MODULE_NAME, wiki, space, page, attachmentName);
        File rootDir = this.environment.getTemporaryDirectory();
        File tempDir = new File(rootDir, path);
        boolean success = (tempDir.exists() || tempDir.mkdirs()) && tempDir.isDirectory() && tempDir.canWrite();
        if (!success) {
            String message = "Error while creating temporary directory [%s] for attachment [%s].";
            throw new Exception(String.format(message, tempDir, attachmentName));
        }
        return tempDir;
    }