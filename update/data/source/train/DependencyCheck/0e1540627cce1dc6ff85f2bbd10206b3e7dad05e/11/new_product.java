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
                for (Identifier i : this.softwareIdentifiers) {
                    if (i instanceof PurlIdentifier) {
                        final PurlIdentifier id = (PurlIdentifier) i;
                        if (mavenArtifact.getArtifactId().equals(id.getName())
                                && mavenArtifact.getGroupId().equals(id.getNamespace())) {
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
        }
        if (!found && mavenArtifact.getGroupId() != null && mavenArtifact.getArtifactId() != null && mavenArtifact.getVersion() != null) {
            try {
                LOGGER.debug("Adding new maven identifier {}", mavenArtifact);
                final PackageURL p = new PackageURL("maven", mavenArtifact.getGroupId(),
                        mavenArtifact.getArtifactId(), mavenArtifact.getVersion(), null, null);
                final PurlIdentifier id = new PurlIdentifier(p, Confidence.HIGHEST);
                this.addSoftwareIdentifier(id);
            } catch (MalformedPackageURLException ex) {
                throw new UnexpectedAnalysisException(ex);
            }
        }
    }