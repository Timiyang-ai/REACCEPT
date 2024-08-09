	@Test
	@Ignore
	public void rotateTest() throws IOException {
		BufferedImage image = ImageUtil.rotate(ImageIO.read(FileUtil.file("d:/logo.png")), 45);
		ImageUtil.write(image, FileUtil.file("d:/result.png"));
	}