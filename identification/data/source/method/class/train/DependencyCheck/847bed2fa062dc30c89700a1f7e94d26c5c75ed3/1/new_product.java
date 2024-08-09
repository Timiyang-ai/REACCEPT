@Override
    protected void analyzeDependency(Dependency dependency, Engine engine) throws AnalysisException {
        String fileVersion = null;
        String pomVersion = null;
        String manifestVersion = null;
        for (Evidence e : dependency.getVersionEvidence()) {
            if (FILE.equals(e.getSource()) && VERSION.equals(e.getName())) {
                fileVersion = e.getValue(Boolean.FALSE);
            } else if ((NEXUS.equals(e.getSource()) || CENTRAL.equals(e.getSource())
                    || POM.equals(e.getSource())) && VERSION.equals(e.getName())) {
                pomVersion = e.getValue(Boolean.FALSE);
            } else if (MANIFEST.equals(e.getSource()) && IMPLEMENTATION_VERSION.equals(e.getName())) {
                manifestVersion = e.getValue(Boolean.FALSE);
            }
        }
        //ensure we have at least two not null
        if (((fileVersion == null ? 0 : 1) + (pomVersion == null ? 0 : 1) + (manifestVersion == null ? 0 : 1)) > 1) {
            final DependencyVersion dvFile = new DependencyVersion(fileVersion);
            final DependencyVersion dvPom = new DependencyVersion(pomVersion);
            final DependencyVersion dvManifest = new DependencyVersion(manifestVersion);
            final boolean fileMatch = Objects.equals(dvFile, dvPom) || Objects.equals(dvFile, dvManifest);
            final boolean manifestMatch = Objects.equals(dvManifest, dvPom) || Objects.equals(dvManifest, dvFile);
            final boolean pomMatch = Objects.equals(dvPom, dvFile) || Objects.equals(dvPom, dvManifest);
            if (fileMatch || manifestMatch || pomMatch) {
                LOGGER.debug("filtering evidence from {}", dependency.getFileName());
                final EvidenceCollection versionEvidence = dependency.getVersionEvidence();
                synchronized (versionEvidence) {
                    final Iterator<Evidence> itr = versionEvidence.iterator();
                    while (itr.hasNext()) {
                        final Evidence e = itr.next();
                        if (!(pomMatch && VERSION.equals(e.getName())
                                && (NEXUS.equals(e.getSource()) || CENTRAL.equals(e.getSource()) || POM.equals(e.getSource())))
                                && !(fileMatch && VERSION.equals(e.getName()) && FILE.equals(e.getSource()))
                                && !(manifestMatch && MANIFEST.equals(e.getSource()) && IMPLEMENTATION_VERSION.equals(e.getName()))) {
                            itr.remove();
                        }
                    }
                }
            }
        }
    }