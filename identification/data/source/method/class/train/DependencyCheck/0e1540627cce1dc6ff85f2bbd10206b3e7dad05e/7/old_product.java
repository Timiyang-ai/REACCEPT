public void process(Dependency dependency) {
        if (filePath != null && !filePath.matches(dependency.getFilePath())) {
            return;
        }
        if (sha1 != null && !sha1.equalsIgnoreCase(dependency.getSha1sum())) {
            return;
        }
        if (gav != null) {
            final Iterator<Identifier> itr = dependency.getIdentifiers().iterator();
            boolean gavFound = false;
            while (itr.hasNext()) {
                final Identifier i = itr.next();
                if (identifierMatches("maven", this.gav, i)) {
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
            for (Identifier i : dependency.getIdentifiers()) {
                for (PropertyType c : this.cpe) {
                    if (identifierMatches("cpe", c, i)) {
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
            for (Identifier i : removalList) {
                dependency.removeIdentifier(i);
            }
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
                        if (v.getCwe() != null) {
                            final String toMatch = String.format("CWE-%s", entry);
                            String toTest = v.getCwe();
                            if (toTest.contains(" ")) {
                                toTest = toTest.substring(0, toTest.indexOf(" ")).toUpperCase();
                            }
                            if (toTest.equals(toMatch)) {
                                remove = true;
                                removeVulns.add(v);
                                break;
                            }
                        }
                    }
                }
                if (!remove) {
                    for (float cvss : this.cvssBelow) {
                        if (v.getCvssScore() < cvss) {
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