public void process(Dependency dependency) {
        if (filePath != null && !filePath.matches(dependency.getFilePath())) {
            return;
        }
        if (sha1 != null && !sha1.equalsIgnoreCase(dependency.getSha1sum())) {
            return;
        }
        if (gav != null) {
            final Iterator<Identifier> itr = dependency.getSoftwareIdentifiers().iterator();
            boolean gavFound = false;
            while (itr.hasNext()) {
                final Identifier i = itr.next();
                if (identifierMatches(this.gav, i)) {
                    gavFound = true;
                    break;
                }
            }
            if (!gavFound) {
                return;
            }
        }

        if (this.hasCpe()) {
            final Set<Identifier> removalList = new HashSet<>();
            for (Identifier i : dependency.getVulnerableSoftwareIdentifiers()) {
                for (PropertyType c : this.cpe) {
                    if (identifierMatches(c, i)) {
                        if (!isBase()) {
                            if (this.notes != null) {
                                i.setNotes(this.notes);
                            }
                            dependency.addSuppressedIdentifier(i);
                        }
                        removalList.add(i);
                        break;
                    }
                }
            }
            removalList.forEach((i) -> {
                dependency.removeVulnerableSoftwareIdentifier(i);
            });
        }
        if (hasCve() || hasCwe() || hasCvssBelow()) {
            final Set<Vulnerability> removeVulns = new HashSet<>();
            for (Vulnerability v : dependency.getVulnerabilities()) {
                boolean remove = false;
                for (String entry : this.cve) {
                    if (entry.equalsIgnoreCase(v.getName())) {
                        removeVulns.add(v);
                        remove = true;
                        break;
                    }
                }
                if (!remove) {
                    for (String entry : this.cwe) {
                        if (v.getCwes() != null) {
                            final String toMatch = String.format("CWE-%s", entry);
                            if (v.getCwes().stream().anyMatch(toTest -> toMatch.regionMatches(0, toTest, 0, toMatch.length()))) {
                                remove = true;
                                removeVulns.add(v);
                                break;
                            }
                        }
                    }
                }
                if (!remove) {
                    for (float cvss : this.cvssBelow) {
                        if (v.getCvssV2().getScore() < cvss) {
                            remove = true;
                            removeVulns.add(v);
                            break;
                        }
                    }
                }
                if (remove) {
                    if (!isBase()) {
                        if (this.notes != null) {
                            v.setNotes(this.notes);
                        }
                        dependency.addSuppressedVulnerability(v);
                    }
                }
            }
            for (Vulnerability v : removeVulns) {
                dependency.removeVulnerability(v);
            }
        }
    }