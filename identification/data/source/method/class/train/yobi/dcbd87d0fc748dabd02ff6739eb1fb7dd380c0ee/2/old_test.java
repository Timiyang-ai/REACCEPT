	@Test
	public void isLoginId() throws Exception {
	    // Given
	    String existingId = "yobi";
	    String nonExistingId = "yobiii";
	    // When
	    boolean result1 = User.isLoginIdExist(existingId);
	    boolean result2 = User.isLoginIdExist(nonExistingId);
	    boolean result3 = User.isLoginIdExist(null);
	    // Then
	    assertThat(result1).isEqualTo(true);
	    assertThat(result2).isEqualTo(false);
	    assertThat(result3).isEqualTo(false);
	}