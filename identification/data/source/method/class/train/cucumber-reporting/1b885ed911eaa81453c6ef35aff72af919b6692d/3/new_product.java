public List<Feature> parseJsonFiles(List<String> jsonFiles) {
        List<Feature> featureResults = new ArrayList<>();

        for (int i = 0; i < jsonFiles.size(); i++) {
            String jsonFile = jsonFiles.get(i);
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