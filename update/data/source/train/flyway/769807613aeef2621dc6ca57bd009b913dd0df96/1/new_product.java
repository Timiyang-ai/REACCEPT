static Collection<ExecutableMigration> collectMigrations(Collection<MigrationResolver> migrationResolvers) {
        Set<ExecutableMigration> migrations = new HashSet<ExecutableMigration>();
        for (MigrationResolver migrationResolver : migrationResolvers) {
            migrations.addAll(migrationResolver.resolveMigrations());
        }
        return migrations;
    }