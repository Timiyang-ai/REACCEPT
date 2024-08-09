public List<Feature> parseJsonResults(List<String> jsonReportFiles) {
        List<Feature> featureResults = new ArrayList<>();
        Gson gson = new Gson();

        for (int i = 0; i < jsonReportFiles.size(); i++) {
            String jsonFile = jsonReportFiles.get(i);
            try (Reader reader = new InputStreamReader(new FileInputStream(jsonFile), Charsets.UTF_8)) {
                Feature[] features = gson.fromJson(reader, Feature[].class);
                if (features == null) {
                    throw new IllegalArgumentException(String.format("File '%s' does not contain features!", jsonFile));
                }
                setMetadata(features, jsonFile, i);

                featureResults.addAll(Arrays.asList(features));
            } catch (IOException | JsonSyntaxException e) {
                throw new ValidationException(e);
            }
        }

        return featureResults;
    }