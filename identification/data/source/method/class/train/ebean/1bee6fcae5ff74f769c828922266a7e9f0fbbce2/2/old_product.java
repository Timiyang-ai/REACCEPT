public void apply(Migration migration, MigrationVersion version) {

    List<ChangeSet> changeSets = migration.getChangeSet();
    for (ChangeSet changeSet : changeSets) {
      boolean pending = changeSet.getType() == ChangeSetType.PENDING_DROPS;
      if (pending) {
        // un-applied drop columns etc
        pendingDrops.add(version, changeSet);
      } else if (isDropsFor(changeSet)) {
        // applied drops (so no longer pending)
        pendingDrops.remove(MigrationVersion.parse(changeSet.getDropsFor()));
      }
      if (!isDropsFor(changeSet)) {
        applyChangeSet(changeSet);
      }
    }
  }