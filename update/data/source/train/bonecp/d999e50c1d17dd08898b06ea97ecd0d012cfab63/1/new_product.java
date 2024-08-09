public void configure(Properties props) throws HibernateException {
		this.config = new BoneCPConfig();
		try {
			// Use reflection to read in all possible properties of int, String or boolean.
			for (Field field: BoneCPConfig.class.getDeclaredFields()){
				if (!Modifier.isFinal(field.getModifiers())){ // avoid logger etc.
					if (field.getType().equals(int.class)){
						Method method = BoneCPConfig.class.getDeclaredMethod("set"+upFirst(field.getName()), int.class);
						String val = props.getProperty("bonecp."+field.getName());
						if (val != null) {
							try{
								method.invoke(this.config, Integer.parseInt(val));
							} catch (NumberFormatException e){
								// do nothing, use the default value
							}
						}
					} if (field.getType().equals(long.class)){
						Method method = BoneCPConfig.class.getDeclaredMethod("set"+upFirst(field.getName()), long.class);
						String val = props.getProperty("bonecp."+field.getName());
						if (val != null) {
							try{
								method.invoke(this.config, Long.parseLong(val));
							} catch (NumberFormatException e){
								// do nothing, use the default value
							}
						}
					} else if (field.getType().equals(String.class)){
						Method method = BoneCPConfig.class.getDeclaredMethod("set"+upFirst(field.getName()), String.class);
						String val = props.getProperty("bonecp."+field.getName());
						if (val != null) {
							method.invoke(this.config, val);
						}
					} else if (field.getType().equals(boolean.class)){
						Method method = BoneCPConfig.class.getDeclaredMethod("set"+upFirst(field.getName()), boolean.class);
						String val = props.getProperty("bonecp."+field.getName());
						if (val != null) {
							method.invoke(this.config, Boolean.parseBoolean(val));
						}
					}
				}
			}

			// old hibernate config
			String url = props.getProperty(CONFIG_CONNECTION_URL);
			String username = props.getProperty(CONFIG_CONNECTION_USERNAME);
			String password = props.getProperty(CONFIG_CONNECTION_PASSWORD);
			String driver = props.getProperty(CONFIG_CONNECTION_DRIVER_CLASS);
			if (url == null){
				url = props.getProperty(CONFIG_CONNECTION_URL_ALTERNATE);
			}
			if (username == null){
				username = props.getProperty(CONFIG_CONNECTION_USERNAME_ALTERNATE);
			}
			if (password == null){
				password = props.getProperty(CONFIG_CONNECTION_PASSWORD_ALTERNATE);
			}
			if (driver == null){
				driver = props.getProperty(CONFIG_CONNECTION_DRIVER_CLASS_ALTERNATE);
			}

			if (url != null){
				this.config.setJdbcUrl(url);
			}
			if (username != null){
				this.config.setUsername(username);
			}
			if (password != null){
				this.config.setPassword(password);
			}


			// Remember Isolation level
			this.isolation = PropertiesHelper.getInteger(Environment.ISOLATION, props);
			this.autocommit = PropertiesHelper.getBoolean(Environment.AUTOCOMMIT, props);

			logger.debug(this.config.toString());

			if (driver != null && !driver.trim().equals("")){
				loadClass(driver);
			}
			if (this.config.getConnectionHookClassName() != null){
				Object hookClass = loadClass(this.config.getConnectionHookClassName()).newInstance();
				this.config.setConnectionHook((ConnectionHook) hookClass);
			}
			// create the connection pool
			this.pool = createPool(this.config);
		} catch (Exception e) {
			throw new HibernateException(e);
		} 
	}