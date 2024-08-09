public void add(MigrationVersion version, ChangeSet changeSet) {

    Entry entry = map.computeIfAbsent(version.normalised(), k -> new Entry(version));
    entry.add(changeSet);
  }