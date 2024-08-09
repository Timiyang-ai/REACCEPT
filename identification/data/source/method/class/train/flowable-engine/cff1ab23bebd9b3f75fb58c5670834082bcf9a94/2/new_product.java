public static boolean noneOf(Object collection, Object value) {

        if (collection == null) {
            throw new IllegalArgumentException("collection cannot be null");
        }

        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }

        // collection to check against
        Collection targetCollection = getTargetCollection(collection, value);

        // elements to check
        if (DMNParseUtil.isParseableCollection(value)) {
            Collection valueCollection = DMNParseUtil.parseCollection(value, targetCollection);
            return !CollectionUtils.containsAny(targetCollection, valueCollection);
        } else if (DMNParseUtil.isJavaCollection(value)) {
            return !CollectionUtils.containsAny(targetCollection, (Collection) value);
        } else if (DMNParseUtil.isArrayNode(value)) {
            Collection valueCollection = DMNParseUtil.getCollectionFromArrayNode((ArrayNode) value);
            return !CollectionUtils.containsAny(targetCollection, valueCollection);
        } else {
            Object formattedValue = DMNParseUtil.getFormattedValue(value, targetCollection);
            return !targetCollection.contains(formattedValue);
        }
    }