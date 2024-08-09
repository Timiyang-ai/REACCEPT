	@Test
	public void write() throws IOException {
		StringBuilderWriter sw = new StringBuilderWriter();
		IOUtil.write("hahahaha", sw);
		assertThat(sw.toString()).isEqualTo("hahahaha");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtil.write("hahahaha", out);
		assertThat(new String(out.toByteArray(), Charsets.UTF_8)).isEqualTo("hahahaha");

		IOUtil.closeQuietly(out);
		IOUtil.closeQuietly((Closeable) null);
	}