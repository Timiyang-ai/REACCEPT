@Override
    protected void analyzeDependency(Dependency dependency, Engine engine) throws AnalysisException {
        String fileVersion = null;
        String pomVersion = null;
        for (Evidence e : dependency.getVersionEvidence()) {
            if ("file".equals(e.getSource()) && "version".equals(e.getName())) {
                fileVersion = e.getValue(Boolean.FALSE);
            } else if (("nexus".equals(e.getSource()) || "central".equals(e.getSource())
                    || "pom".equals(e.getSource())) && "version".equals(e.getName())) {
                pomVersion = e.getValue(Boolean.FALSE);
            }
        }
        if (fileVersion != null && pomVersion != null) {
            final DependencyVersion dvFile = new DependencyVersion(fileVersion);
            final DependencyVersion dvPom = new DependencyVersion(pomVersion);
            if (dvPom.equals(dvFile)) {
                LOGGER.debug("filtering evidence from {}", dependency.getFileName());
                final EvidenceCollection versionEvidence = dependency.getVersionEvidence();
                synchronized (versionEvidence) {
                    final Iterator<Evidence> itr = versionEvidence.iterator();
                    while (itr.hasNext()) {
                        final Evidence e = itr.next();
                        if (!("version".equals(e.getName())
                                && ("file".equals(e.getSource())
                                || "nexus".equals(e.getSource())
                                || "central".equals(e.getSource())
                                || "pom".equals(e.getSource())))) {
                            itr.remove();
                        }
                    }
                }
            }
        }
    }