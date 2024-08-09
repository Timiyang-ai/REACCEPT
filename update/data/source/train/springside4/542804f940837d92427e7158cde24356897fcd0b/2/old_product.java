@Override
	public SearchUserResponse searchUser(String loginName, String name) {
		SearchUserResponse response = new SearchUserResponse();
		try {

			Map<String, Object> parameters = Maps.newHashMap();
			parameters.put("loginName", loginName);
			parameters.put("name", name);
			List<User> userList = accountDao.searchUser(parameters);

			List<UserDTO> dtoList = BeanMapper.mapList(userList, UserDTO.class);
			response.setUserList(dtoList);
			return response;
		} catch (RuntimeException e) {
			return handleGeneralError(response, e);
		}
	}