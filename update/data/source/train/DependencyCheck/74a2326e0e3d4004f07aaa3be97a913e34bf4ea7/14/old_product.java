@Override
    public void analyzeDependency(Dependency dependency, Engine engine) throws AnalysisException {
        final File f = new File(dependency.getActualFilePath());
        final File tmpDir = getNextTempDirectory();
        extractFiles(f, tmpDir, engine);

        //make a copy
        final List<Dependency> dependencySet = findMoreDependencies(engine, tmpDir);

        if (dependencySet != null && !dependencySet.isEmpty()) {
            for (Dependency d : dependencySet) {
                if (d.getFilePath().startsWith(tmpDir.getAbsolutePath())) {
                    //fix the dependency's display name and path
                    final String displayPath = String.format("%s%s",
                            dependency.getFilePath(),
                            d.getActualFilePath().substring(tmpDir.getAbsolutePath().length()));
                    final String displayName = String.format("%s: %s",
                            dependency.getFileName(),
                            d.getFileName());
                    d.setFilePath(displayPath);
                    d.setFileName(displayName);
                    d.setProjectReferences(dependency.getProjectReferences());

                    //TODO - can we get more evidence from the parent? EAR contains module name, etc.
                    //analyze the dependency (i.e. extract files) if it is a supported type.
                    if (this.accept(d.getActualFile()) && scanDepth < MAX_SCAN_DEPTH) {
                        scanDepth += 1;
                        analyze(d, engine);
                        scanDepth -= 1;
                    }
                } else {
                    for (Dependency sub : dependencySet) {
                        if (sub.getFilePath().startsWith(tmpDir.getAbsolutePath())) {
                            final String displayPath = String.format("%s%s",
                                    dependency.getFilePath(),
                                    sub.getActualFilePath().substring(tmpDir.getAbsolutePath().length()));
                            final String displayName = String.format("%s: %s",
                                    dependency.getFileName(),
                                    sub.getFileName());
                            sub.setFilePath(displayPath);
                            sub.setFileName(displayName);
                        }
                    }
                }
            }
        }
        if (REMOVE_FROM_ANALYSIS.accept(dependency.getActualFile())) {
            addDisguisedJarsToDependencies(dependency, engine);
            engine.getDependencies().remove(dependency);
        }
        Collections.sort(engine.getDependencies());
    }