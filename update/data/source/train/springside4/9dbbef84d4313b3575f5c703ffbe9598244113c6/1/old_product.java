public List<UserDTO> getAllUser() {
		String authentication = Servlets.encodeHttpBasic("admin", "admin");
		return client.path("/users").header(Servlets.AUTHENTICATION_HEADER, authentication)
				.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<UserDTO>>() {
				});
	}