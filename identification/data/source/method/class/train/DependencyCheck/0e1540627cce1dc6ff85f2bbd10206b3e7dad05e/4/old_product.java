public void addAsEvidence(String source, MavenArtifact mavenArtifact, Confidence confidence) {
        if (mavenArtifact.getGroupId() != null && !mavenArtifact.getGroupId().isEmpty()) {
            this.addEvidence(EvidenceType.VENDOR, source, "groupid", mavenArtifact.getGroupId(), confidence);
        }
        if (mavenArtifact.getArtifactId() != null && !mavenArtifact.getArtifactId().isEmpty()) {
            this.addEvidence(EvidenceType.PRODUCT, source, "artifactid", mavenArtifact.getArtifactId(), confidence);
        }
        if (mavenArtifact.getVersion() != null && !mavenArtifact.getVersion().isEmpty()) {
            this.addEvidence(EvidenceType.VERSION, source, "version", mavenArtifact.getVersion(), confidence);
        }
        boolean found = false;
        if (mavenArtifact.getArtifactUrl() != null && !mavenArtifact.getArtifactUrl().isEmpty()) {
            synchronized (this) {
                for (Identifier i : this.identifiers) {
                    if ("maven".equals(i.getType()) && i.getValue().equals(mavenArtifact.toString())) {
                        found = true;
                        i.setConfidence(Confidence.HIGHEST);
                        final String url = "http://search.maven.org/#search|ga|1|1%3A%22" + this.getSha1sum() + "%22";
                        i.setUrl(url);
                        //i.setUrl(mavenArtifact.getArtifactUrl());
                        LOGGER.debug("Already found identifier {}. Confidence set to highest", i.getValue());
                        break;
                    }
                }
            }
        }
        if (!found && mavenArtifact.getGroupId() != null && mavenArtifact.getArtifactId() != null && mavenArtifact.getVersion() != null) {
            LOGGER.debug("Adding new maven identifier {}", mavenArtifact);
            this.addIdentifier("maven", mavenArtifact.toString(), mavenArtifact.getArtifactUrl(), Confidence.HIGHEST);
        }
    }