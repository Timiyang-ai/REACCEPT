public String getDeployedFileName() {
    String fileBaseName = JarDeployer.getDeployedFileBaseName(this.file.getName());
    if (fileBaseName == null) {
      throw new IllegalStateException("file name needs to have a sequence number");
    }
    return fileBaseName + ".jar";
  }