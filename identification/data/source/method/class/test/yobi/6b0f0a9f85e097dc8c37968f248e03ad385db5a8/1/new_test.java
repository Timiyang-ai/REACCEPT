	@Test
	public void findByLoginId() throws Exception {
	    // Given
	    // When
	    User user = User.findByLoginId("laziel");
	    // Then
	    assertThat(user.id).isEqualTo(3l);
	}