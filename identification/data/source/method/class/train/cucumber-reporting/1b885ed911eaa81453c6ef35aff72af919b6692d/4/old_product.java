public List<Feature> parseJsonResults(List<String> jsonReportFiles) {
        List<Feature> featureResults = new ArrayList<>();

        for (int i = 0; i < jsonReportFiles.size(); i++) {
            String jsonFile = jsonReportFiles.get(i);
            Feature[] features = parseForFeature(jsonFile);
            if (ArrayUtils.isEmpty(features)) {
                LOG.info(String.format("File '%s' does not contain features", jsonFile));
            } else {
                setMetadata(features, jsonFile, i);
                featureResults.addAll(Arrays.asList(features));
            }
        }

        return featureResults;
    }