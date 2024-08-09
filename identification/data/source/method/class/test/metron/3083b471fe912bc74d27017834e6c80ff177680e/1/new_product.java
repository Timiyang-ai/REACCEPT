public void uploadConfig(String path) throws Exception {
    configUploadComponent
            .withGlobalConfiguration(path)
            .withProfilerConfiguration(path)
            .update();
  }