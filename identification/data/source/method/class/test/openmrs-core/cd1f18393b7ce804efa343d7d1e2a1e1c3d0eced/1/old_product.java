public static boolean storeLocale(String locale) {
		if (StringUtils.isNotBlank(locale)) {
			
			Connection connection = null;
			Integer userId = null;
			try {
				connection = DatabaseUpdater.getConnection();
				
				// first we should try to get admin user id
				userId = getUserIdByName(ADMIN_USERNAME, connection);
				
				// first we are saving locale as administrative user's property
				if (userId != null) {
					String insert = "insert into user_property (user_id, property, property_value) values (?, 'defaultLocale', ?)";
					PreparedStatement statement = connection.prepareStatement(insert);
					statement.setInt(1, userId);
					statement.setString(2, locale);
					if (statement.executeUpdate() != 1) {
						log.warn("Unable to save user locale as admin property.");
					}
					
				}
				
				// and the second step is to save locale as system default locale global property
				String update = "update global_property set property_value = ? where property = ? ";
				PreparedStatement statement = connection.prepareStatement(update);
				statement.setString(1, locale);
				statement.setString(2, OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCALE);
				if (statement.executeUpdate() != 1) {
					log.warn("Unable to set system default locale property.");
				}
			}
			catch (Throwable t) {
				log.warn("Locale " + locale + " could not be set for user with id " + userId + " .", t);
				return false;
			}
			finally {
				if (connection != null) {
					try {
						connection.close();
					}
					catch (SQLException e) {
						log.debug("Error while closing the database", e);
					}
				}
			}
			return true;
		}
		return false;
	}