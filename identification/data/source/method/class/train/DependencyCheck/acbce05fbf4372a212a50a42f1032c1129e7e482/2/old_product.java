public void process(Dependency dependency) {
        if (filePath != null && !filePath.matches(dependency.getFilePath())) {
            return;
        }
        if (sha1 != null && !sha1.equalsIgnoreCase(dependency.getSha1sum())) {
            return;
        }
        if (this.hasCpe()) {
            final Iterator<Identifier> itr = dependency.getIdentifiers().iterator();
            while (itr.hasNext()) {
                final Identifier i = itr.next();
                for (PropertyType c : this.cpe) {
                    if (cpeMatches(c, i)) {
                        dependency.addSuppressedIdentifier(i);
                        itr.remove();
                        break;
                    }
                }
            }
        }
        if (hasCve() || hasCwe() || hasCvssBelow()) {
            final Iterator<Vulnerability> itr = dependency.getVulnerabilities().iterator();
            while (itr.hasNext()) {
                boolean remove = false;
                final Vulnerability v = itr.next();
                for (String entry : this.cve) {
                    if (entry.equalsIgnoreCase(v.getName())) {
                        remove = true;
                        break;
                    }
                }
                if (!remove) {
                    for (String entry : this.cwe) {
                        if (v.getCwe() != null) {
                            final String toMatch = String.format("CWE-%s ", entry);
                            final String toTest = v.getCwe().substring(0, toMatch.length()).toUpperCase();
                            if (toTest.equals(toMatch)) {
                                remove = true;
                                break;
                            }
                        }
                    }
                }
                if (!remove) {
                    for (float cvss : this.cvssBelow) {
                        if (v.getCvssScore() < cvss) {
                            remove = true;
                            break;
                        }
                    }
                }
                if (remove) {
                    dependency.addSuppressedVulnerability(v);
                    itr.remove();
                }
            }
        }
    }