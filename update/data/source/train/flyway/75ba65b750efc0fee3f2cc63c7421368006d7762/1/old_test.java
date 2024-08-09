@Test
    public void collectMigrations() {
        MigrationResolver migrationResolver = new MigrationResolver() {
            public List<ExecutableMigration> resolveMigrations() {
                List<ExecutableMigration> migrations = new ArrayList<ExecutableMigration>();

                migrations.add(createTestMigration(com.googlecode.flyway.core.api.MigrationType.JAVA, "1", "Description", "Migration1", 123));
                migrations.add(createTestMigration(com.googlecode.flyway.core.api.MigrationType.JAVA, "1", "Description", "Migration1", 123));
                migrations.add(createTestMigration(com.googlecode.flyway.core.api.MigrationType.SQL, "2", "Description2", "Migration2", 1234));
                return migrations;
            }
        };
        Collection<MigrationResolver> migrationResolvers = new ArrayList<MigrationResolver>();
        migrationResolvers.add(migrationResolver);

        Collection<ExecutableMigration> migrations = CompositeMigrationResolver.collectMigrations(migrationResolvers);
        assertEquals(2, migrations.size());
    }