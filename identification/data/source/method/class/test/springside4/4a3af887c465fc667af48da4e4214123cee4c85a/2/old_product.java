@Override
	public GetUserResponse getUser(Long id) {
		GetUserResponse response = new GetUserResponse();
		try {

			Validate.notNull(id, "id参数为空");

			User user = accountService.getUser(id);

			Validate.notNull(user, "用户不存在(id:" + id + ")");

			UserDTO dto = BeanMapper.map(user, UserDTO.class);
			response.setUser(dto);

			return response;

		} catch (IllegalArgumentException e) {
			return handleParameterError(response, e);
		} catch (RuntimeException e) {
			return handleGeneralError(response, e);
		}
	}