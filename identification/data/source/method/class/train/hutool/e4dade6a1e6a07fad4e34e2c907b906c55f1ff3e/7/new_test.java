	@Test
	@Ignore
	public void cutTest() {
		ImageUtil.cut(FileUtil.file("d:/face.jpg"), FileUtil.file("d:/face_result.jpg"), new Rectangle(200, 200, 100, 100));
	}