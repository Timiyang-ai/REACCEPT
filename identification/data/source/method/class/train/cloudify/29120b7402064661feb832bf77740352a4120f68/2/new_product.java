public String put(final String fileName, final MultipartFile multipartFile) throws IOException, RestErrorException {
        String name = fileName == null ? multipartFile.getOriginalFilename() : fileName;
        // enforce size limit
        long fileSize = multipartFile.getSize();
        if (fileSize > getUploadSizeLimitBytes()) {
            logger.warning("Upload file [" + name + "] size ("
                    + fileSize + ") exceeded the permitted size limit (" + getUploadSizeLimitBytes() + ").");
            throw new RestErrorException(
                    CloudifyMessageKeys.UPLOAD_FILE_SIZE_LIMIT_EXCEEDED.getName(),
                    name, fileSize, getUploadSizeLimitBytes());
        }
        final String dirName = UUID.randomUUID().toString();
        final File srcDir = new File(restUploadDir, dirName);
        srcDir.mkdirs();
        final File storedFile = new File(srcDir, name);
        copyMultipartFileToLocalFile(multipartFile, storedFile);


        logger.finer("File [" + storedFile.getAbsolutePath() + "] uploaded.");
        return dirName;
    }