private void remove(final String id, final StringBuilder sql) {
        sql.append("delete from ").append(getName()).append(" where ").append(JdbcRepositories.OID).append("='").append(id).append("'");
    }