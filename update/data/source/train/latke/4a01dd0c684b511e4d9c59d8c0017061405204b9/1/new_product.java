private void remove(final String id, final StringBuilder sql) {
        sql.append("delete from ").append(getName()).append(" where ").append(JdbcRepositories.getDefaultKeyName()).append("='").append(id).append(
            "'");
    }