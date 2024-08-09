@Test
	public void createUser() {
		User user = UserData.randomUser();
		UserDTO userDTO = BeanMapper.map(user, UserDTO.class);

		IdResult response = accountWebServiceClient.createUser(userDTO);
		assertNotNull(response.getId());
		GetUserResponse response2 = accountWebServiceClient.getUser(response.getId());
		assertEquals(user.getLoginName(), response2.getUser().getLoginName());
	}