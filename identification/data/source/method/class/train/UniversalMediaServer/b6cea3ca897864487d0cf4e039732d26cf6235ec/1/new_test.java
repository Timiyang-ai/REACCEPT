@Test
	public void testGetFileNameWithRewriting() throws Exception {
		// Video of a TV episode
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.S01E02.720p.mkv", null)).isEqualTo("Universal Media Server - 102");

		// Video of a TV episode in double-digit seasons
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.S12E03.720p.mkv", null)).isEqualTo("Universal Media Server - 1203");

		// Video spanning two TV episodes
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.S01E02E03.720p.mkv", null)).isEqualTo("Universal Media Server - 102-103");

		// Video of an extended TV episode
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.S01E02.EXTENDED.720p.mkv", null)).isEqualTo("Universal Media Server - 102");

		// Video of a TV episode with the "Mysterious Wordplay" title
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.S01E02.Mysterious.Wordplay.720p.mkv", null)).isEqualTo("Universal Media Server - 102 - Mysterious Wordplay");

		// Video of an uncut TV episode
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.S01E02.UNCUT.720p.mkv", null)).isEqualTo("Universal Media Server - 102 (Uncut)");

		// Video of an extended cut of a TV episode
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.S01E02.Extended.Cut.720p.mkv", null)).isEqualTo("Universal Media Server - 102 (Extended Cut)");

		// Video of a TV episode that airs very regularly
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.2015.01.23.720p.mkv", null)).isEqualTo("Universal Media Server - 2015/01/23");

		// Video of a TV episode that airs very regularly and has an episode title
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.2015.01.23.Mysterious.Wordplay.720p.mkv", null)).isEqualTo("Universal Media Server - 2015/01/23 - Mysterious Wordplay");

		// Video of a movie
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.2015.720p.mkv", null)).isEqualTo("Universal Media Server (2015)");

		// Video of a special edition of a movie
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.Special.Edition.2015.720p.mkv", null)).isEqualTo("Universal Media Server (2015) (Special Edition)");
		assertThat(FileUtil.getFileNamePrettified("Universal.Media.Server.2015.Special.Edition.720p.mkv", null)).isEqualTo("Universal Media Server (2015) (Special Edition)");
	}