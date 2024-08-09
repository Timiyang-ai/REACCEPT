protected void removeBadMatches(Dependency dependency) {

        /* TODO - can we utilize the pom's groupid and artifactId to filter??? most of
         * these are due to low quality data.  Other idea would be to say any CPE
         * found based on LOW confidence evidence should have a different CPE type? (this
         * might be a better solution then just removing the URL for "best-guess" matches).
         */
        //Set<Evidence> groupId = dependency.getVendorEvidence().getEvidence("pom", "groupid");
        //Set<Evidence> artifactId = dependency.getVendorEvidence().getEvidence("pom", "artifactid");
        for (Identifier i : dependency.getIdentifiers()) {
            //TODO move this startsWith expression to the base suppression file
            if ("cpe".equals(i.getType())) {
                if ((i.getValue().matches(".*c\\+\\+.*")
                        || i.getValue().startsWith("cpe:/a:file:file")
                        || i.getValue().startsWith("cpe:/a:mozilla:mozilla")
                        || i.getValue().startsWith("cpe:/a:cvs:cvs")
                        || i.getValue().startsWith("cpe:/a:ftp:ftp")
                        || i.getValue().startsWith("cpe:/a:tcp:tcp")
                        || i.getValue().startsWith("cpe:/a:ssh:ssh")
                        || i.getValue().startsWith("cpe:/a:lookup:lookup"))
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
                    //itr.remove();
                    dependency.removeIdentifier(i);
                } else if ((i.getValue().startsWith("cpe:/a:jquery:jquery")
                        || i.getValue().startsWith("cpe:/a:prototypejs:prototype")
                        || i.getValue().startsWith("cpe:/a:yahoo:yui"))
                        && (dependency.getFileName().toLowerCase().endsWith(".jar")
                        || dependency.getFileName().toLowerCase().endsWith("pom.xml")
                        || dependency.getFileName().toLowerCase().endsWith(".dll")
                        || dependency.getFileName().toLowerCase().endsWith(".exe"))) {
                    //itr.remove();
                    dependency.removeIdentifier(i);
                } else if ((i.getValue().startsWith("cpe:/a:microsoft:excel")
                        || i.getValue().startsWith("cpe:/a:microsoft:word")
                        || i.getValue().startsWith("cpe:/a:microsoft:visio")
                        || i.getValue().startsWith("cpe:/a:microsoft:powerpoint")
                        || i.getValue().startsWith("cpe:/a:microsoft:office")
                        || i.getValue().startsWith("cpe:/a:core_ftp:core_ftp"))
                        && (dependency.getFileName().toLowerCase().endsWith(".jar")
                        || dependency.getFileName().toLowerCase().endsWith(".ear")
                        || dependency.getFileName().toLowerCase().endsWith(".war")
                        || dependency.getFileName().toLowerCase().endsWith("pom.xml"))) {
                    //itr.remove();
                    dependency.removeIdentifier(i);
                } else if (i.getValue().startsWith("cpe:/a:apache:maven")
                        && !dependency.getFileName().toLowerCase().matches("maven-core-[\\d\\.]+\\.jar")) {
                    //itr.remove();
                    dependency.removeIdentifier(i);
                } else if (i.getValue().startsWith("cpe:/a:m-core:m-core")) {
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
                        //itr.remove();
                        dependency.removeIdentifier(i);
                    }
                } else if (i.getValue().startsWith("cpe:/a:jboss:jboss")
                        && !dependency.getFileName().toLowerCase().matches("jboss-?[\\d\\.-]+(GA)?\\.jar")) {
                    //itr.remove();
                    dependency.removeIdentifier(i);
                }
            }
        }
    }