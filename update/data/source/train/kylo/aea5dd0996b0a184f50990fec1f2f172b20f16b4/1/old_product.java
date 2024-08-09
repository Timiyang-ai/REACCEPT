public void dropTable(String table) {
        // Make managed to remove the old data
        String makeManagedSQL = "alter table " + table + " SET TBLPROPERTIES ('EXTERNAL'='FALSE')";
        doExecuteSQL(makeManagedSQL);
        String sql = "DROP TABLE " + table;
        doExecuteSQL(sql);
    }