public User authenticate(String login, String password) throws ContextAuthenticationException {
		
		String errorMsg = "Invalid username and/or password: " + login;
		
		Session session = sessionFactory.getCurrentSession();
		
		User candidateUser = null;
		
		if (login != null) {
			
			// loginWithoutDash is used to compare to the system id
			String loginWithDash = login;
			if (login.matches("\\d{2,}"))
				loginWithDash = login.substring(0, login.length() - 1) + "-" + login.charAt(login.length() - 1);
			
			try {
				candidateUser = (User) session.createQuery(
				    "from User u where (u.username = ? or u.systemId = ? or u.systemId = ?) and u.voided = 0").setString(0,
				    login).setString(1, login).setString(2, loginWithDash).uniqueResult();
			}
			catch (HibernateException he) {
				log.error("Got hibernate exception while logging in: '" + login + "'", he);
			}
			catch (Exception e) {
				log.error("Got regular exception while logging in: '" + login + "'", e);
			}
		}
		
		// only continue if this is a valid username and a nonempty password
		if (candidateUser != null && password != null) {
			if (log.isDebugEnabled())
				log.debug("Candidate user id: " + candidateUser.getUserId());
			
			String lockoutTimeString = candidateUser.getUserProperty(OpenmrsConstants.USER_PROPERTY_LOCKOUT_TIMESTAMP, null);
			Long lockoutTime = null;
			if (lockoutTimeString != null && !lockoutTimeString.equals("0"))
				lockoutTime = Long.valueOf(lockoutTimeString);
			
			// if they've been locked out, don't continue with the authentication
			if (lockoutTime != null) {
				// unlock them after 5 mins, otherwise reset the timestamp 
				// to now and make them wait another 5 mins 
				if (new Date().getTime() - lockoutTime > 300000)
					candidateUser.setUserProperty(OpenmrsConstants.USER_PROPERTY_LOGIN_ATTEMPTS, "0");
				else {
					candidateUser.setUserProperty(OpenmrsConstants.USER_PROPERTY_LOCKOUT_TIMESTAMP, String
					        .valueOf(new Date().getTime()));
					throw new ContextAuthenticationException(
					        "Invalid number of connection attempts. Please try again later.");
				}
			}
			
			String passwordOnRecord = (String) session.createSQLQuery("select password from users where user_id = ?")
			        .addScalar("password", Hibernate.STRING).setInteger(0, candidateUser.getUserId()).uniqueResult();
			
			String saltOnRecord = (String) session.createSQLQuery("select salt from users where user_id = ?").addScalar(
			    "salt", Hibernate.STRING).setInteger(0, candidateUser.getUserId()).uniqueResult();
			
			String hashedPassword = Security.encodeString(password + saltOnRecord);
			
			// if the username and password match, hydrate the user and return it
			if (hashedPassword != null && hashedPassword.equals(passwordOnRecord)) {
				// hydrate the user object
				candidateUser.getAllRoles().size();
				candidateUser.getUserProperties().size();
				candidateUser.getPrivileges().size();
				
				// only clean up if the were some login failures, otherwise all should be clean
				Integer attempts = getUsersLoginAttempts(candidateUser);
				if (attempts > 0) {
					candidateUser.setUserProperty(OpenmrsConstants.USER_PROPERTY_LOGIN_ATTEMPTS, "0");
					candidateUser.removeUserProperty(OpenmrsConstants.USER_PROPERTY_LOCKOUT_TIMESTAMP);
					saveUserProperties(candidateUser);
				}
				
				// skip out of the method early (instead of throwing the exception)
				// to indicate that this is the valid user
				return candidateUser;
			} else {
				// the user failed the username/password, increment their
				// attempts here and set the "lockout" timestamp if necessary
				Integer attempts = getUsersLoginAttempts(candidateUser);
				
				attempts++;
				
				if (attempts >= 5) {
					// set the user as locked out at this exact time
					candidateUser.setUserProperty(OpenmrsConstants.USER_PROPERTY_LOCKOUT_TIMESTAMP, String
					        .valueOf(new Date().getTime()));
				} else {
					candidateUser.setUserProperty(OpenmrsConstants.USER_PROPERTY_LOGIN_ATTEMPTS, String.valueOf(attempts));
				}
				
				saveUserProperties(candidateUser);
			}
		}
		
		// throw this exception only once in the same place with the same
		// message regardless of username/pw combo entered
		log.info("Failed login attempt (login=" + login + ") - " + errorMsg);
		throw new ContextAuthenticationException(errorMsg);
		
	}