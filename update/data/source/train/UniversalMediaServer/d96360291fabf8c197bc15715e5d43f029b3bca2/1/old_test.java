@Test
	public void testGetFileNameWithRewriting() throws Exception {
		assertThat(FileUtil.getFileNameWithRewriting("Universal.Media.Server.S01E01E02.720p.mkv", null)).isEqualTo("Universal Media Server - 101-102");
	}