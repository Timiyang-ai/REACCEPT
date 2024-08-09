public static String getDisplayNameFromDiscoFeed(String entityIdToFind, String discoFeed) {
        JsonParser jsonParser = new JsonParser();
        JsonElement root = jsonParser.parse(discoFeed);
        JsonArray identityProviders = root.getAsJsonArray();
        for (JsonElement identityProvider : identityProviders) {
            JsonObject provider = identityProvider.getAsJsonObject();
            JsonElement entityId = provider.get("entityID");
            if (entityId != null) {
                if (entityId.getAsString().equals(entityIdToFind)) {
                    JsonArray displayNames = provider.get("DisplayNames").getAsJsonArray();
                    JsonElement firstDisplayName = displayNames.get(0);
                    String friendlyName = firstDisplayName.getAsJsonObject().get("value").getAsString();
                    return friendlyName;
                }
            }
        }
        return null;
    }