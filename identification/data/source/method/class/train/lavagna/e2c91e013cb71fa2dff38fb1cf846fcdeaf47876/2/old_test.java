	@Test
	public void getFile() throws IOException {
		when(cardDataRepository.getUndeletedFileByCardDataId(itemId)).thenReturn(fileDataLight);
		when(response.getOutputStream()).thenReturn(mock(ServletOutputStream.class));
		cardDataController.getFile(itemId, response);
	}