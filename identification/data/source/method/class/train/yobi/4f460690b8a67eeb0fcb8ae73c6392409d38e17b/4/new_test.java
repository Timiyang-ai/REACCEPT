	@Test
	public void findUsers() throws Exception {
	    // Given
	    // When
	    Page<User> searchUsers = User.findUsers(0, "yobi", UserState.ACTIVE);
	    // Then
	    assertThat(searchUsers.getTotalRowCount()).isEqualTo(1);
	}