public void dropTable(@Nonnull final String schema, @Nonnull final String table) {
        // Make managed to remove the old data
        String makeManagedSQL = "alter table " + HiveUtils.quoteIdentifier(schema, table) + " SET TBLPROPERTIES ('EXTERNAL'='FALSE')";
        doExecuteSQL(makeManagedSQL);
        String sql = "DROP TABLE " + HiveUtils.quoteIdentifier(schema, table);
        doExecuteSQL(sql);
    }