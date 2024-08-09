public StringList backups(final String db) {
    final StringList backups = new StringList();
    final IOFile file = soptions.dbpath(db + IO.ZIPSUFFIX);
    if(file.exists()) {
      backups.add(db);
    } else {
      final Pattern regex = regex(db, '-' + DATE + '\\' + IO.ZIPSUFFIX);
      for(final IOFile f : soptions.dbpath().children()) {
        final String n = f.name();
        if(regex.matcher(n).matches()) backups.add(n.substring(0, n.lastIndexOf('.')));
      }
    }
    return backups.sort(Prop.CASE, false);
  }