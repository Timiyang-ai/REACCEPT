public static void remove(BValue json, String fieldName) {
        if (json == null || json.getType().getTag() != TypeTags.JSON_TAG) {
            return;
        }

        ((BMap<String, ?>) json).remove(fieldName);
    }