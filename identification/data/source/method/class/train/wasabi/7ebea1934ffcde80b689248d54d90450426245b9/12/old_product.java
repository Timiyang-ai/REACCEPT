@Override
    public void setUserRole(UserRole userRole) {

        Preconditions.checkNotNull(userRole, "Parameter \"userRole\" cannot be null");
        String cql = "insert into user_roles (user_id, app_name, role) values (?, ?, ?)";
        try {
            driver.getKeyspace()
                    .prepareQuery(keyspace.userRolesCF())
                    .withCql(cql)
                    .asPreparedStatement()
                    .withByteBufferValue(userRole.getUserID(), UsernameSerializer.get())
                    .withByteBufferValue(userRole.getApplicationName(), ApplicationNameSerializer.get())
                    .withStringValue(userRole.getRole().toString())
                    .execute();

        } catch (Exception e) {
            throw new RepositoryException("Could not set roles for user \"" + userRole.getUserID() + "\"", e);
        }

        cql = "insert into app_roles (app_name, user_id, role) values (?, ?, ?)";
        try {
            driver.getKeyspace()
                    .prepareQuery(keyspace.userRolesCF())
                    .withCql(cql)
                    .asPreparedStatement()
                    .withByteBufferValue(userRole.getApplicationName(), ApplicationNameSerializer.get())
                    .withByteBufferValue(userRole.getUserID(), UsernameSerializer.get())
                    .withStringValue(userRole.getRole().toString())
                    .execute();

        } catch (Exception e) {
            //delete the record from the first table
            cql = "delete from user_roles where user_id = ? and app_name = ?";
            try {
                driver.getKeyspace().prepareQuery(keyspace.appRoleCF())
                        .withCql(cql)
                        .asPreparedStatement()
                        .withByteBufferValue(userRole.getApplicationName(), ApplicationNameSerializer.get())
                        .withStringValue(userRole.getRole().toString())
                        .execute();

            } catch (Exception e2) {
                throw new RepositoryException("Could not rollback role for user " + userRole.getRole().toString() +
                        "in application \"" + userRole.getApplicationName() + "\"", e2);
            }
            throw new RepositoryException("Could not set roles for user \"" + userRole.getUserID() + "\"", e);
        }
    }