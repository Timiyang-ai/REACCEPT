@Override
	public UserListResponse searchUser(String loginName, String name) {
		try {
			List<User> entityList = accountManager.searchUser(loginName, name);

			List<UserDTO> dtoList = BeanMapper.mapList(entityList, UserDTO.class);

			return new UserListResponse(dtoList);
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			return new UserListResponse().setDefaultError();
		}
	}