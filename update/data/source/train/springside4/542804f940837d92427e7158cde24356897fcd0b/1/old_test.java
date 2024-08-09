@Test
	public void createUser() {
		User user = UserData.randomUser();

		UserDTO userDTO = new UserDTO();
		userDTO.setLoginName(user.getLoginName());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());

		IdResponse result = accountWebServiceClient.createUser(userDTO);
		assertNotNull(result.getId());
	}