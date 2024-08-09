public static String getDisplayNameFromDiscoFeed(String entityIdToFind, String discoFeed) {
        JsonParser jsonParser = new JsonParser();
        JsonElement root = jsonParser.parse(discoFeed);
        JsonArray identityProviders = root.getAsJsonArray();
        for (JsonElement identityProvider : identityProviders) {
            JsonObject provider = identityProvider.getAsJsonObject();
            JsonElement entityId = provider.get("entityID");
            if (entityId != null) {
                if (entityId.getAsString().equals(entityIdToFind)) {
                    JsonElement displayNamesElement = provider.get("DisplayNames");
                    if (displayNamesElement != null) {
                        JsonArray displayNamesArray = displayNamesElement.getAsJsonArray();
                        JsonElement firstDisplayName = displayNamesArray.get(0);
                        if (firstDisplayName != null) {
                            JsonObject friendlyNameObject = firstDisplayName.getAsJsonObject();
                            if (friendlyNameObject != null) {
                                JsonElement friendlyNameElement = friendlyNameObject.get("value");
                                if (friendlyNameElement != null) {
                                    String friendlyName = friendlyNameElement.getAsString();
                                    return friendlyName;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }