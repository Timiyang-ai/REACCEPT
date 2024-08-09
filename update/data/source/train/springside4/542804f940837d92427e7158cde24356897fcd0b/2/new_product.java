@Override
	public SearchUserResponse searchUser(String loginName, String name) {
		SearchUserResponse response = new SearchUserResponse();
		try {
			List<User> userList = accountService.searchUser(loginName, name);

			List<UserDTO> dtoList = BeanMapper.mapList(userList, UserDTO.class);
			response.setUserList(dtoList);
			return response;
		} catch (RuntimeException e) {
			return handleGeneralError(response, e);
		}
	}