@Nonnull
    public static JdbcFunction<ResultSet, DefaultJdbcTable> fromResultSet(@Nullable final DatabaseMetaData databaseMetaData) {
        return new JdbcFunction<ResultSet, DefaultJdbcTable>() {
            @Override
            public DefaultJdbcTable apply(final ResultSet resultSet) throws SQLException {
                return fromResultSet(resultSet, databaseMetaData);
            }
        };
    }