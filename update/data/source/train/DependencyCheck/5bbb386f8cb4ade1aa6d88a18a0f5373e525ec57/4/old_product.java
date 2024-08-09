public void addAsEvidence(String source, MavenArtifact mavenArtifact, Confidence confidence) {
        if (mavenArtifact.getGroupId() != null && !mavenArtifact.getGroupId().isEmpty()) {
            this.getVendorEvidence().addEvidence(source, "groupid", mavenArtifact.getGroupId(), confidence);
        }
        if (mavenArtifact.getArtifactId() != null && !mavenArtifact.getArtifactId().isEmpty()) {
            this.getProductEvidence().addEvidence(source, "artifactid", mavenArtifact.getArtifactId(), confidence);
        }
        if (mavenArtifact.getVersion() != null && !mavenArtifact.getVersion().isEmpty()) {
            this.getVersionEvidence().addEvidence(source, "version", mavenArtifact.getVersion(), confidence);
        }
        if (mavenArtifact.getArtifactUrl() != null && !mavenArtifact.getArtifactUrl().isEmpty()) {
            boolean found = false;
            for (Identifier i : this.getIdentifiers()) {
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
            if (!found) {
                LOGGER.debug("Adding new maven identifier {}", mavenArtifact);
                this.addIdentifier("maven", mavenArtifact.toString(), mavenArtifact.getArtifactUrl(), Confidence.HIGHEST);
            }
        }
    }