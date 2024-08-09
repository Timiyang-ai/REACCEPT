public List<Feature> parseJsonResults(List<String> jsonReportFiles) {
        List<Feature> featureResults = new ArrayList<>();

        for (int i = 0; i < jsonReportFiles.size(); i++) {
            String jsonFile = jsonReportFiles.get(i);
            Feature[] features = parseForFeature(jsonFile);
            if (ArrayUtils.isEmpty(features)) {
                LOG.info("File '{}' does not contain features", jsonFile);
            } else {
                LOG.info("File '{}' contain {} features", jsonFile, features.length);
                setMetadata(features, jsonFile, i);
                featureResults.addAll(Arrays.asList(features));
            }
        }

        return featureResults;
    }