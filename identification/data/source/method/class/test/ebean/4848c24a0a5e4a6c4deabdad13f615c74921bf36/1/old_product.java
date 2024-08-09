public Migration migrationForVersion(String pendingVersion) {

    Entry entry = getChangeSets(pendingVersion);

    Migration migration = new Migration();
    for (ChangeSet changeSet : entry.list) {
      changeSet.setType(ChangeSetType.APPLY);
      changeSet.setDropsFor(entry.version.asString());
      migration.getChangeSet().add(changeSet);
    }

    return migration;
  }