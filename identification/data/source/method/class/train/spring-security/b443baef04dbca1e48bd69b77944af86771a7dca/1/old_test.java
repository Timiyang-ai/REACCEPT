	@Test
	public void getAuthoritiesMapper() throws Exception {
		assertThat(configurer.getAuthoritiesMapper()).isInstanceOf(SimpleAuthorityMapper.class);
		configurer.authoritiesMapper(new NullAuthoritiesMapper());
		assertThat(configurer.getAuthoritiesMapper()).isInstanceOf(NullAuthoritiesMapper.class);

	}