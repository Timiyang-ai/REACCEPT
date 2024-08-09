	@Test
	public void crc32() {
		assertThat(HashUtil.crc32AsInt("hahhha1")).isEqualTo(-625925593);
		assertThat(HashUtil.crc32AsInt("hahhha1".getBytes())).isEqualTo(-625925593);
		assertThat(HashUtil.crc32AsInt("hahhha2")).isEqualTo(1136161693);

		assertThat(HashUtil.crc32AsLong("hahhha1")).isEqualTo(3669041703L);
		assertThat(HashUtil.crc32AsLong("hahhha1".getBytes())).isEqualTo(3669041703L);
		assertThat(HashUtil.crc32AsLong("hahhha2")).isEqualTo(1136161693L);
	}