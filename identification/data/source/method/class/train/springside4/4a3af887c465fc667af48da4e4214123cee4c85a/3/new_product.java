@Override
	public IdResult createUser(UserDTO user) {
		IdResult result = new IdResult();
		try {
			Validate.notNull(user, "用户参数为空");

			User userEntity = BeanMapper.map(user, User.class);
			BeanValidators.validateWithException(validator, userEntity);

			accountService.saveUser(userEntity);

			return new IdResult(userEntity.getId());
		} catch (ConstraintViolationException e) {
			String message = StringUtils.join(BeanValidators.extractPropertyAndMessageAsList(e, " "), "\n");
			return handleParameterError(result, e, message);
		} catch (RuntimeException e) {
			if (Exceptions.isCausedBy(e, org.hibernate.exception.ConstraintViolationException.class)) {
				String message = "新建用户参数存在唯一性冲突(用户:" + user + ")";
				return handleParameterError(result, e, message);
			} else {
				return handleGeneralError(result, e);
			}
		}
	}