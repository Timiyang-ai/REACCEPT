public String downloadPackage(HeliumPackage pkg, String[] nameAndVersion, File bundleDir,
                                String templateWebpackConfig, String templatePackageJson,
                                FrontendPluginFactory fpf) throws IOException, TaskRunnerException {
    if (bundleDir.exists()) {
      FileUtils.deleteQuietly(bundleDir);
    }
    FileUtils.forceMkdir(bundleDir);

    FileFilter copyFilter = new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        String fileName = pathname.getName();
        if (fileName.startsWith(".") || fileName.startsWith("#") || fileName.startsWith("~")) {
          return false;
        } else {
          return true;
        }
      }
    };

    if (isLocalPackage(pkg)) {
      FileUtils.copyDirectory(
              new File(pkg.getArtifact()),
              bundleDir,
              copyFilter);
    } else {
      // if online package
      String version = nameAndVersion[1];
      File tgz = new File(heliumLocalRepoDirectory, pkg.getName() + "-" + version + ".tgz");
      tgz.delete();

      // wget, extract and move dir to `bundles/${pkg.getName()}`, and remove tgz
      npmCommand(fpf, "pack " + pkg.getArtifact());
      File extracted = new File(heliumBundleDirectory, "package");
      FileUtils.deleteDirectory(extracted);
      unTgz(tgz, heliumBundleDirectory);
      tgz.delete();
      FileUtils.copyDirectory(extracted, bundleDir);
      FileUtils.deleteDirectory(extracted);
    }

    // 1. setup package.json
    File existingPackageJson = new File(bundleDir, "package.json");
    JsonReader reader = new JsonReader(new FileReader(existingPackageJson));
    Map<String, Object> packageJson = gson.fromJson(reader,
            new TypeToken<Map<String, Object>>(){}.getType());
    Map<String, String> existingDeps = (Map<String, String>) packageJson.get("dependencies");
    String mainFileName = (String) packageJson.get("main");

    StringBuilder dependencies = new StringBuilder();
    int index = 0;
    for (Map.Entry<String, String> e: existingDeps.entrySet()) {
      dependencies.append("    \"").append(e.getKey()).append("\": ");
      if (e.getKey().equals("zeppelin-vis") ||
          e.getKey().equals("zeppelin-tabledata") ||
          e.getKey().equals("zeppelin-spell")) {
        dependencies.append("\"file:../../" + HELIUM_LOCAL_MODULE_DIR + "/")
                .append(e.getKey()).append("\"");
      } else {
        dependencies.append("\"").append(e.getValue()).append("\"");
      }

      if (index < existingDeps.size() - 1) {
        dependencies.append(",\n");
      }
      index = index + 1;
    }

    FileUtils.deleteQuietly(new File(bundleDir, PACKAGE_JSON));
    templatePackageJson = templatePackageJson.replaceFirst("PACKAGE_NAME", pkg.getName());
    templatePackageJson = templatePackageJson.replaceFirst("MAIN_FILE", mainFileName);
    templatePackageJson = templatePackageJson.replaceFirst("DEPENDENCIES", dependencies.toString());
    FileUtils.write(new File(bundleDir, PACKAGE_JSON), templatePackageJson);

    // 2. setup webpack.config
    FileUtils.write(new File(bundleDir, "webpack.config.js"), templateWebpackConfig);

    return mainFileName;
  }