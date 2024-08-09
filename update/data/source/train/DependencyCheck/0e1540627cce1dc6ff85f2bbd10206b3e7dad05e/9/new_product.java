protected void removeBadMatches(Dependency dependency) {

        /* TODO - can we utilize the pom's groupid and artifactId to filter??? most of
         * these are due to low quality data.  Other idea would be to say any CPE
         * found based on LOW confidence evidence should have a different CPE type? (this
         * might be a better solution then just removing the URL for "best-guess" matches).
         */
        //Set<Evidence> groupId = dependency.getVendorEvidence().getEvidence("pom", "groupid");
        //Set<Evidence> artifactId = dependency.getVendorEvidence().getEvidence("pom", "artifactid");
        for (Identifier i : dependency.getVulnerableSoftwareIdentifiers()) {
            //TODO move this startsWith expression to the base suppression file
            if (i instanceof CpeIdentifier) {
                final CpeIdentifier cpeId = (CpeIdentifier) i;
                final Cpe cpe = cpeId.getCpe();
                if ((cpe.getProduct().matches(".*c\\+\\+.*")
                        || ("file".equals(cpe.getVendor()) && "file".equals(cpe.getProduct()))
                        || ("mozilla".equals(cpe.getVendor()) && "mozilla".equals(cpe.getProduct()))
                        || ("cvs".equals(cpe.getVendor()) && "cvs".equals(cpe.getProduct()))
                        || ("ftp".equals(cpe.getVendor()) && "ftp".equals(cpe.getProduct()))
                        || ("tcp".equals(cpe.getVendor()) && "tcp".equals(cpe.getProduct()))
                        || ("ssh".equals(cpe.getVendor()) && "ssh".equals(cpe.getProduct()))
                        || ("lookup".equals(cpe.getVendor()) && "lookup".equals(cpe.getProduct())))
                        && (dependency.getFileName().toLowerCase().endsWith(".jar")
                        || dependency.getFileName().toLowerCase().endsWith("pom.xml")
                        || dependency.getFileName().toLowerCase().endsWith(".dll")
                        || dependency.getFileName().toLowerCase().endsWith(".exe")
                        || dependency.getFileName().toLowerCase().endsWith(".nuspec")
                        || dependency.getFileName().toLowerCase().endsWith(".zip")
                        || dependency.getFileName().toLowerCase().endsWith(".sar")
                        || dependency.getFileName().toLowerCase().endsWith(".apk")
                        || dependency.getFileName().toLowerCase().endsWith(".tar")
                        || dependency.getFileName().toLowerCase().endsWith(".gz")
                        || dependency.getFileName().toLowerCase().endsWith(".tgz")
                        || dependency.getFileName().toLowerCase().endsWith(".ear")
                        || dependency.getFileName().toLowerCase().endsWith(".war"))) {
                    dependency.removeVulnerableSoftwareIdentifier(i);
                } else if ((("jquery".equals(cpe.getVendor()) && "jquery".equals(cpe.getProduct()))
                        || ("prototypejs".equals(cpe.getVendor()) && "prototype".equals(cpe.getProduct()))
                        || ("yahoo".equals(cpe.getVendor()) && "yui".equals(cpe.getProduct())))
                        && (dependency.getFileName().toLowerCase().endsWith(".jar")
                        || dependency.getFileName().toLowerCase().endsWith("pom.xml")
                        || dependency.getFileName().toLowerCase().endsWith(".dll")
                        || dependency.getFileName().toLowerCase().endsWith(".exe"))) {
                    dependency.removeVulnerableSoftwareIdentifier(i);
                } else if ((("microsoft".equals(cpe.getVendor()) && "excel".equals(cpe.getProduct()))
                        || ("microsoft".equals(cpe.getVendor()) && "word".equals(cpe.getProduct()))
                        || ("microsoft".equals(cpe.getVendor()) && "visio".equals(cpe.getProduct()))
                        || ("microsoft".equals(cpe.getVendor()) && "powerpoint".equals(cpe.getProduct()))
                        || ("microsoft".equals(cpe.getVendor()) && "office".equals(cpe.getProduct()))
                        || ("core_ftp".equals(cpe.getVendor()) && "core_ftp".equals(cpe.getProduct())))
                        && (dependency.getFileName().toLowerCase().endsWith(".jar")
                        || dependency.getFileName().toLowerCase().endsWith(".ear")
                        || dependency.getFileName().toLowerCase().endsWith(".war")
                        || dependency.getFileName().toLowerCase().endsWith("pom.xml"))) {
                    dependency.removeVulnerableSoftwareIdentifier(i);
                } else if (("apache".equals(cpe.getVendor()) && "maven".equals(cpe.getProduct()))
                        && !dependency.getFileName().toLowerCase().matches("maven-core-[\\d\\.]+\\.jar")) {
                    dependency.removeVulnerableSoftwareIdentifier(i);
                } else if (("m-core".equals(cpe.getVendor()) && "m-core".equals(cpe.getProduct()))) {
                    boolean found = false;
                    for (Evidence e : dependency.getEvidence(EvidenceType.PRODUCT)) {
                        if ("m-core".equalsIgnoreCase(e.getValue())) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        for (Evidence e : dependency.getEvidence(EvidenceType.VENDOR)) {
                            if ("m-core".equalsIgnoreCase(e.getValue())) {
                                found = true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        dependency.removeVulnerableSoftwareIdentifier(i);
                    }
                } else if (("jboss".equals(cpe.getVendor()) && "jboss".equals(cpe.getProduct()))
                        && !dependency.getFileName().toLowerCase().matches("jboss-?[\\d\\.-]+(GA)?\\.jar")) {
                    dependency.removeVulnerableSoftwareIdentifier(i);
                }
            }
        }
    }