@ExpectPermission(Permission.READ)
    @RequestMapping(value = "/api/card-data/file/{fileId}/{ignore:.+}", method = RequestMethod.GET)
    public void getFile(@PathVariable("fileId") int fileId, HttpServletResponse response) {
        FileDataLight fileData = cardDataRepository.getUndeletedFileByCardDataId(fileId);
        try (OutputStream out = response.getOutputStream()) {
            if (WHITE_LIST_MIME_TYPES.contains(fileData.getContentType())) {
                response.setContentType(fileData.getContentType());
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=\"" + fileData.getName() + "\"");
                response.setContentType("application/octet-stream");
            }
            cardDataRepository.outputFileContent(fileData.getDigest(), out);
        } catch (IOException e) {
            LOG.error("error getting file", e);
            response.setStatus(500);
        }
    }