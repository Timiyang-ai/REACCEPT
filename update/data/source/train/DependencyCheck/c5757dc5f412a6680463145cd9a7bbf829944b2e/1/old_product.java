protected void scanArtifacts(MavenProject project, Engine engine) {
        for (Artifact a : project.getArtifacts()) {
            if (excludeFromScan(a)) {
                continue;
            }
            final List<Dependency> deps = engine.scan(a.getFile().getAbsoluteFile());
            if (deps != null) {
                if (deps.size() == 1) {
                    final Dependency d = deps.get(0);
                    if (d != null) {
                        final MavenArtifact ma = new MavenArtifact(a.getGroupId(), a.getArtifactId(), a.getVersion());
                        d.addAsEvidence("pom", ma, Confidence.HIGHEST);
                        d.addProjectReference(project.getName());
                        if (getLog().isDebugEnabled()) {
                            getLog().debug(String.format("Adding project reference %s on dependency %s", project.getName(),
                                    d.getDisplayFileName()));
                        }
                    }
                } else if (getLog().isDebugEnabled()) {
                    final String msg = String.format("More then 1 dependency was identified in first pass scan of '%s:%s:%s'",
                            a.getGroupId(), a.getArtifactId(), a.getVersion());
                    getLog().debug(msg);
                }
            }
        }
    }