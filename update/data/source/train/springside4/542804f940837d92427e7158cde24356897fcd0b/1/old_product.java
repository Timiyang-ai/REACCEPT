@Override
	public IdResponse createUser(UserDTO user) {
		IdResponse response = new IdResponse();
		try {
			Validate.notNull(user, "用户参数为空");

			User userEntity = BeanMapper.map(user, User.class);
			BeanValidators.validateWithException(validator, userEntity);

			accountDao.saveUser(userEntity);

			return new IdResponse(userEntity.getId());
		} catch (ConstraintViolationException e) {
			String message = StringUtils.join(BeanValidators.extractPropertyAndMessageAsList(e, " "), "\n");
			return handleParameterError(response, e, message);
		} catch (DataIntegrityViolationException e) {
			String message = "新建用户参数存在唯一性冲突(用户:" + user + ")";
			return handleParameterError(response, e, message);
		} catch (RuntimeException e) {
			return handleGeneralError(response, e);
		}
	}