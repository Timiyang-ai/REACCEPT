public void apply(Migration migration) {

    List<ChangeSet> changeSets = migration.getChangeSet();
    for (ChangeSet changeSet : changeSets) {
      applyChangeSet(changeSet);
    }
  }