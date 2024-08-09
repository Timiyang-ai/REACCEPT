public boolean appliedDropsFor(MigrationVersion version) {
    Entry entry = map.get(version.normalised());
    if (entry.removeDrops()) {
      // it had no suppressForever changeSets so remove completely
      map.remove(version.normalised());
      return true;
    }

    return false;
  }