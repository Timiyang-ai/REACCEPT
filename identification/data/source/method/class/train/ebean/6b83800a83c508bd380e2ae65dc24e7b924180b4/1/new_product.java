static String lastVersion(File migDirectory, File modelDir) {

    List<MigrationVersion> versions = new ArrayList<>();

    File[] sqlFiles = migDirectory.listFiles(pathname -> includeSqlFile(pathname.getName().toLowerCase()));
    if (sqlFiles != null) {
      for (File file : sqlFiles) {
        versions.add(trimAndParse(file.getName()));
      }
    }

    if (modelDir != null) {
      File[] xmlFiles = modelDir.listFiles(pathname -> includeModelFile(pathname.getName().toLowerCase()));
      if (xmlFiles != null) {
        for (File file : xmlFiles) {
          versions.add(trimAndParse(file.getName()));
        }
      }
    }

    Collections.sort(versions);
    if (!versions.isEmpty()) {
      return versions.get(versions.size() - 1).asString();
    }
    return null;
  }