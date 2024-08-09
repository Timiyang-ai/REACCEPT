static Collection<Migration> collectMigrations(Collection<MigrationResolver> migrationResolvers) {
        Set<Migration> migrations = new HashSet<Migration>();
        for (MigrationResolver migrationResolver : migrationResolvers) {
            migrations.addAll(migrationResolver.resolveMigrations());
        }
        return migrations;
    }