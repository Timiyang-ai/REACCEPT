public void update(String path) throws Exception {
    configUploadComponent.withGlobalConfiguration(path)
        .withProfilerConfiguration(path);
    configUploadComponent.update();
  }