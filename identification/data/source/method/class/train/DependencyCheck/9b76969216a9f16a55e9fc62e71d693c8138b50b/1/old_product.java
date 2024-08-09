public String getCoordinates() {
        if (path == null) {
            return null;
        }
        final String[] parts = path.split("/");
        if (parts.length < 4) {
            return null;
        }
        final String cordVersion = parts[parts.length - 2];
        final String cordArtifactId = parts[parts.length - 3];
        StringBuilder sb = new StringBuilder();
        String period = "";
        for (int i = 0; i < parts.length - 3; i++) {
            sb.append(period).append(parts[i]);
            if (period.isEmpty()) {
                period = ".";
            }
        }
        return String.format("%s:%s:%s", sb.toString(), cordArtifactId, cordVersion);

//        if (!packageName.contains(":") && name != null) {
//            int pos = FilenameUtils.indexOfExtension(name);
//            String artifactId;
//            if (pos > 1) {
//                artifactId = name.substring(0, pos);
//            } else {
//                artifactId = name;
//            }
//            if (artifactId.endsWith(version)) {
//                artifactId = artifactId.substring(0, artifactId.length() - version.length() - 1);
//            } else {
//                final String dependencyVersion = DependencyVersionUtil.parseVersion(artifactId).toString();
//                if (dependencyVersion!=null) {
//                    artifactId = artifactId.substring(0, artifactId.length() - dependencyVersion.length() - 1);    
//                }
//            }
//            if (path != null) {
//                String tmp = path.replace("/", ".");
//                pos = tmp.lastIndexOf("." + artifactId + ".");
//                if (pos > 0) {
//                    tmp = tmp.substring(0, pos);
//                    return String.format("%s:%s:%s", tmp, artifactId, version);
//                }
//            }
//
//            return String.format("%s:%s:%s", packageName, artifactId, version);
//        }
//        return String.format("%s:%s", packageName, version);
    }