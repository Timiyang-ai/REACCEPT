public List<UserDTO> getAllUser(String userName, String password) {
		String authentication = Servlets.encodeHttpBasic(userName, password);
		return client.path("/users").header(Servlets.AUTHENTICATION_HEADER, authentication)
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<UserDTO>>() {
				});
	}