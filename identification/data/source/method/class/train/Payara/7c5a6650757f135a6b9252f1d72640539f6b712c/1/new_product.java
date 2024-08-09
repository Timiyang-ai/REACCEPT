void getListOfFiles(File directory, List<String> files, List embeddedArchives, final Logger logger) {
        if(directory == null || !directory.isDirectory())
            return;
        final File[] fileList = directory.listFiles();
        if (fileList == null) {
            deplLogger.log(Level.WARNING,
                           FILE_LIST_FAILURE,
                           directory.getAbsolutePath());
             return;
        }
        for (File aList : fileList) {
            String fileName = aList.getAbsolutePath().substring(archive.getAbsolutePath().length() + 1);
            fileName = fileName.replace(File.separatorChar, '/');
            if (!aList.isDirectory()) {
                if (!fileName.equals(JarFile.MANIFEST_NAME) && isEntryValid(fileName, logger)) {
                    files.add(fileName);
                }
            } else if (isEntryValid(fileName, logger)) {
                files.add(fileName); // Add entry corresponding to the directory also to the list
                if (embeddedArchives != null) {
                    if (!embeddedArchives.contains(fileName)) {
                        getListOfFiles(aList, files, null, logger);
                    }
                } else {
                    getListOfFiles(aList, files, null, logger);
                }
            }
        }
    }