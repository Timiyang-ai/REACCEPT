@Override
    public UserPermissions checkSuperAdminPermissions(UserInfo.Username userID, Application.Name applicationName) {
        String cql = "select * from user_roles where user_id = ? and app_name = '*'";
        OperationResult<CqlResult<UserInfo.Username, String>> opResult;

        try {
            opResult =
                    driver.getKeyspace()
                            .prepareQuery(keyspace.userRolesCF())
                            .withCql(cql)
                            .asPreparedStatement()
                            .withByteBufferValue(userID, UsernameSerializer.get())
                            .execute();

        } catch (ConnectionException e) {
            throw new RepositoryException("Could not retrieve permissions for user \"" + userID + "\" and application " +
                    "\"" + applicationName + "\"", e);
        }

        Rows<UserInfo.Username, String> rows = opResult.getResult().getRows();

        UserPermissions userPermissions;
        if (!rows.isEmpty()) {
            for (Row<UserInfo.Username, String> row : rows) {
                if ("superadmin".equals(row.getColumns().getStringValue("role", ""))) {
                    userPermissions = UserPermissions.newInstance(applicationName, Role.SUPERADMIN.getRolePermissions())
                            .build();
                    return userPermissions;
                }
            }
        }
        return null;
    }