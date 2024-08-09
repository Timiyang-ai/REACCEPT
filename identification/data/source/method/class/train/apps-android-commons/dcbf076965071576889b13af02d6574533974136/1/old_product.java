public static List<String> extractCategoriesFromList(String source) {
        if (StringUtils.isNullOrWhiteSpace(source)) {
            return new ArrayList<>();
        }
        String[] cats = source.split("\\|");
        List<String> categories = new ArrayList<>();
        for (String category : cats) {
            if (!StringUtils.isNullOrWhiteSpace(category.trim())) {
                categories.add(category);
            }
        }
        return categories;
    }