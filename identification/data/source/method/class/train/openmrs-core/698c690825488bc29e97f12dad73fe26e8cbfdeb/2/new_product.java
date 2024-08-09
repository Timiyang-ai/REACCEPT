@Override
	public void validate(Object object, Errors errors) throws DAOException {
		Class entityClass = object.getClass();
		ClassMetadata metadata = sessionFactory.getClassMetadata(entityClass);
		if (metadata != null) {
			String[] propNames = metadata.getPropertyNames();
			Object identifierType = metadata.getIdentifierType();
			String identifierName = metadata.getIdentifierPropertyName();
			if (identifierType instanceof StringType || identifierType instanceof TextType) {
				int maxLength = getMaximumPropertyLength(entityClass, identifierName);
				String identifierValue = (String) metadata.getIdentifier(object,
				    (SessionImplementor) sessionFactory.getCurrentSession());
				if (identifierValue != null) {
					int identifierLength = identifierValue.length();
					if (identifierLength > maxLength) {
						
						errors.rejectValue(identifierName, "error.exceededMaxLengthOfField", new Object[] { maxLength },
						    null);
					}
				}
			}
			for (String propName : propNames) {
				Type propType = metadata.getPropertyType(propName);
				if (propType instanceof StringType || propType instanceof TextType) {
					String propertyValue = (String) metadata.getPropertyValue(object, propName);
					if (propertyValue != null) {
						int maxLength = getMaximumPropertyLength(entityClass, propName);
						int propertyValueLength = propertyValue.length();
						if (propertyValueLength > maxLength) {
							errors.rejectValue(propName, "error.exceededMaxLengthOfField", new Object[] { maxLength },
									null);
						}
					}
				}
			}
		}
		FlushMode previousFlushMode = sessionFactory.getCurrentSession().getFlushMode();
		sessionFactory.getCurrentSession().setFlushMode(FlushMode.MANUAL);
		try {
			for (Validator validator : getValidators(object)) {
				validator.validate(object, errors);
			}
			
		}
		
		finally {
			sessionFactory.getCurrentSession().setFlushMode(previousFlushMode);
		}
		
	}