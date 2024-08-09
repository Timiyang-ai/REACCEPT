@Override
	public SearchUserResult searchUser(String loginName, String name) {
		SearchUserResult result = new SearchUserResult();
		try {
			List<User> userList = accountService.searchUser(loginName, name);

			List<UserDTO> dtoList = BeanMapper.mapList(userList, UserDTO.class);
			result.setUserList(dtoList);
			return result;
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
	}