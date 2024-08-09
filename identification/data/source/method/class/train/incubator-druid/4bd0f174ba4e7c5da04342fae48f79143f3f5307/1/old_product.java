public static File[] getExtensionFilesToLoad(ExtensionsConfig config)
  {
    final File rootExtensionsDir = new File(config.getDirectory());
    if (rootExtensionsDir.exists() && !rootExtensionsDir.isDirectory()) {
      throw new ISE("Root extensions directory [%s] is not a directory!?", rootExtensionsDir);
    }
    File[] extensionsToLoad;
    final List<String> toLoad = config.getLoadList();
    if (toLoad == null) {
      extensionsToLoad = rootExtensionsDir.listFiles();
    } else {
      int i = 0;
      extensionsToLoad = new File[toLoad.size()];
      for (final String extensionName : toLoad) {
        File extensionDir = new File(extensionName);
        if (!extensionDir.isAbsolute()) {
          extensionDir = new File(rootExtensionsDir, extensionName);
        }

        if (!extensionDir.isDirectory()) {
          throw new ISE(
              "Extension [%s] specified in \"druid.extensions.loadList\" didn't exist!?",
              extensionDir.getAbsolutePath()
          );
        }
        extensionsToLoad[i++] = extensionDir;
      }
    }
    return extensionsToLoad == null ? new File[]{} : extensionsToLoad;
  }