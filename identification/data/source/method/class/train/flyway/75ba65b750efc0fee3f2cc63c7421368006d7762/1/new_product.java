static Collection<MigrationInfoImpl> collectMigrations(Collection<MigrationResolver> migrationResolvers) {
        Set<MigrationInfoImpl> migrations = new HashSet<MigrationInfoImpl>();
        for (MigrationResolver migrationResolver : migrationResolvers) {
            migrations.addAll(migrationResolver.resolveMigrations());
        }
        return migrations;
    }