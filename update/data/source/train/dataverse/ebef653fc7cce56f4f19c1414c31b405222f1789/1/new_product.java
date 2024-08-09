public JsonObject uploadProvJsonForBundle(JsonObject provJson, String bundleName) throws UnirestException {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("bundle_name", bundleName);
        job.add("JSON", provJson);
        JsonObject jsonToSend = job.build();
        logger.info("to send: " + JsonUtil.prettyPrint(jsonToSend.toString()));
        HttpResponse<JsonNode> response = Unirest.post(provBaseUrl + "/provapi/json")
                .header("Content-Type", "application/json")
                .body(jsonToSend.toString())
                .asJson();
        if (response.getStatus() != 200) {
            throw new RuntimeException("Response code for provenance postBundle: " + response.getStatus() + ", " + response.getStatusText());
        }
        JSONObject orgJsonObject = response.getBody().getObject();
        /**
         * Yes, there is some inefficiency in converting this to the standard
         * (JRS 353) JSON-P format but it's worth using the standard to avoid
         * having to deal with a proliferation of Java implementations for JSON.
         */
        JsonReader jsonReader = Json.createReader(new StringReader(orgJsonObject.toString()));
        return jsonReader.readObject();
    }