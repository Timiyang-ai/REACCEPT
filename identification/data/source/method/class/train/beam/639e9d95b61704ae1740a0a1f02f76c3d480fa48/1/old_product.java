protected final List<String> generateDestinationFilenames(int numFiles) {
      List<String> destFilenames = new ArrayList<>();
      String extension = getSink().extension;
      String baseOutputFilename = getSink().baseOutputFilename;
      String fileNamingTemplate = getSink().fileNamingTemplate;

      String suffix = (extension.length() == 0) ? extension : ("." + extension);
      for (int i = 0; i < numFiles; i++) {
        destFilenames.add(IOChannelUtils.constructName(
            baseOutputFilename, fileNamingTemplate, suffix, i, numFiles));
      }
      return destFilenames;
    }