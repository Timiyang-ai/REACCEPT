public boolean appliedDropsFor(ChangeSet changeSet) {

    MigrationVersion version = MigrationVersion.parse(changeSet.getDropsFor());

    Entry entry = map.get(version.normalised());
    if (entry.removeDrops(changeSet)) {
      // it had no suppressForever changeSets so remove completely
      map.remove(version.normalised());
      return true;
    }

    return false;
  }