public void add(MigrationVersion version, ChangeSet changeSet) {

    Entry entry = map.get(version.normalised());
    if (entry == null) {
      entry = new Entry(version);
      map.put(version.normalised(), entry);
    }
    entry.add(changeSet);
  }