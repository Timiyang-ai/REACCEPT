@Nonnull
    public static <T extends JdbcTable> JdbcFunction<ResultSet, T> fromResultSet(@Nullable final DatabaseMetaData databaseMetaData) {
        return new JdbcFunction<ResultSet, T>() {
            @Override
            @SuppressWarnings("unchecked")
            public T apply(final ResultSet resultSet) throws SQLException {
                return (T) fromResultSet(resultSet, databaseMetaData);
            }
        };
    }