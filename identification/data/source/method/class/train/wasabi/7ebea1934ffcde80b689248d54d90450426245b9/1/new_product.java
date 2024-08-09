@Override
    public UserRoleList getApplicationUsers(Application.Name applicationName) {
        checkNotNull(applicationName, APPLICATION_NON_NULL_MSG);

        final String cql = "select * from app_roles where app_name = ?";

        try {
            OperationResult<CqlResult<Application.Name, String>> opResult =
                    driver.getKeyspace()
                            .prepareQuery(keyspace.appRoleCF())
                            .withCql(cql)
                            .asPreparedStatement()
                            .withByteBufferValue(applicationName, ApplicationNameSerializer.get())
                            .execute();

            Rows<Application.Name, String> rows = opResult.getResult().getRows();
            UserRoleList userRoleList = new UserRoleList();

            for (Row<Application.Name, String> row : rows) {
                Application.Name appName = Application.Name.valueOf(row.getColumns().getStringValue("app_name", ""));
                Role role = Role.toRole(row.getColumns().getStringValue("role", ""));
                UserInfo.Username userID = UserInfo.Username.valueOf(row.getColumns().getStringValue("user_id", ""));
                UserInfo userInfo = getUserInfo(userID);

                if (userInfo == null) {
                    userInfo = lookupUser(userID);
                }

                UserRole userRole = UserRole.newInstance(appName, role)
                        .withUserID(userID)
                        .withUserEmail(userInfo.getEmail())
                        .withFirstName(userInfo.getFirstName())
                        .withLastName(userInfo.getLastName())
                        .build();
                userRoleList.addRole(userRole);
            }

            return userRoleList;
        } catch (ConnectionException e) {
            throw new RepositoryException("Could not retrieve roles for application name \"" + applicationName + "\"", e);
        }
    }