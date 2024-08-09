@Test
    public void migratePrimaryKeyTableIfNeeded_primaryKeyTableMigratedWithRightName() throws IOException {
        List<String> tableNames = Arrays.asList(
                "ChatList", "Drafts", "Member", "Message", "Notifs", "NotifyLink", "PopularPost",
                "Post", "Tags", "Threads", "User");

        configFactory.copyRealmFromAssets(context, "0841_pk_migration.realm", "default.realm");
        sharedRealm = SharedRealm.getInstance(config);
        Table.migratePrimaryKeyTableIfNeeded(sharedRealm);

        Table table = sharedRealm.getTable("pk");
        for (int i = 0; i < table.size(); i++) {
            UncheckedRow row = table.getUncheckedRow(i);
            // io_realm_internal_Table_PRIMARY_KEY_CLASS_COLUMN_INDEX 0LL
            assertTrue(tableNames.contains(row.getString(0)));
        }
    }