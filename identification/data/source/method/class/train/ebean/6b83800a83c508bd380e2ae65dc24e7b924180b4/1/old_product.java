static String lastVersion(File migDirectory, File modelDir) {

    List<String> fileNames = new ArrayList<>();

    File[] sqlFiles = migDirectory.listFiles(pathname -> includeSqlFile(pathname.getName().toLowerCase()));
    if (sqlFiles != null) {
      for (File file : sqlFiles) {
        fileNames.add(trim(file.getName()));
      }
    }

    if (modelDir != null) {
      File[] xmlFiles = modelDir.listFiles(pathname -> includeModelFile(pathname.getName().toLowerCase()));
      if (xmlFiles != null) {
        for (File file : xmlFiles) {
          fileNames.add(trim(file.getName()));
        }
      }
    }

    Collections.sort(fileNames);
    if (!fileNames.isEmpty()) {
      return fileNames.get(fileNames.size() - 1);
    }
    return null;
  }