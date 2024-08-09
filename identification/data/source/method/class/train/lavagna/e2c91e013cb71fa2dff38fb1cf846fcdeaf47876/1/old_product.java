@ExpectPermission(Permission.READ)
    @RequestMapping(value = "/api/card-data/file/{fileId}/{ignore:.+}", method = RequestMethod.GET)
    public void getFile(@PathVariable("fileId") int fileId, HttpServletResponse response) {
        FileDataLight fileData = cardDataRepository.getUndeletedFileByCardDataId(fileId);
        try (OutputStream out = response.getOutputStream()) {
            response.setContentType(fileData.getContentType());
            cardDataRepository.outputFileContent(fileData.getDigest(), out);
        } catch (IOException e) {
            LOG.error("error getting file", e);
            response.setStatus(500);
        }
    }