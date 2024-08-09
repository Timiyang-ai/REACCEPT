public Migration migrationForVersion(String pendingVersion) {

    Entry entry = getEntry(pendingVersion);

    Migration migration = new Migration();
    Iterator<ChangeSet> it = entry.list.iterator();
    while (it.hasNext()) {
      ChangeSet changeSet = it.next();
      if (!isSuppressForever(changeSet)) {
        it.remove();
        changeSet.setType(ChangeSetType.APPLY);
        changeSet.setDropsFor(entry.version.asString());
        migration.getChangeSet().add(changeSet);
      }
    }

    if (migration.getChangeSet().isEmpty()) {
      throw new IllegalArgumentException("The remaining pendingDrops changeSets in migration ["+pendingVersion+"] are suppressDropsForever=true and can't be applied");
    }

    if (!entry.containsSuppressForever()) {
      // we can remove it completely as it has no suppressForever changes
      map.remove(entry.version.normalised());
    }

    return migration;
  }