public static void remove(BValue json, String fieldName) {
        if (json == null || json.getType().getTag() != TypeTags.MAP_TAG) {
            return;
        }

        ((BMap) json).remove(fieldName);
    }