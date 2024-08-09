	@Test
	@Ignore
	public void scaleTest() {
		ImageUtil.scale(FileUtil.file("d:/face.jpg"), FileUtil.file("d:/face_result.jpg"), 0.5f);
	}