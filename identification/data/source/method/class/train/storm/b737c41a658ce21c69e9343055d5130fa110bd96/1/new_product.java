public static Exclusion createExclusion(String exclusionString) {
        String[] parts = exclusionString.split(":");

        String artifactId = "*";
        String classifier = "*";
        String extension = "*";

        int len = parts.length;
        if (len > 1) {
            artifactId = parts[1];
        }
        if (len > 2) {
            classifier = parts[2];
        }
        if (len > 3) {
            extension = parts[3];
        }

        // length of parts should be greater than 0
        String groupId = parts[0];

        return new Exclusion(groupId, artifactId, classifier, extension);
    }