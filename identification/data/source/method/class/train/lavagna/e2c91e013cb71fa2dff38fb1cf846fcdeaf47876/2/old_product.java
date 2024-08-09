@ExpectPermission(Permission.READ)
	@RequestMapping(value = "/api/card-data/file/{fileId}", method = RequestMethod.GET)
	public void getFile(@PathVariable("fileId") int fileId, HttpServletResponse response) {
		FileDataLight fileData = cardDataRepository.getUndeletedFileByCardDataId(fileId);
		response.addHeader("Content-Disposition", "attachment;filename=\"" + fileData.getName() + "\"");
		try (OutputStream out = response.getOutputStream()) {
			cardDataRepository.outputFileContent(fileData.getDigest(), out);
			response.setContentType(fileData.getContentType());
		} catch (IOException e) {
			LOG.error("error getting file", e);
			response.setStatus(500);
		}
	}