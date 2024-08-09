public static List<String> extractCategoriesFromList(String source) {
        if (StringUtils.isBlank(source)) {
            return new ArrayList<>();
        }
        String[] cats = source.split("\\|");
        List<String> categories = new ArrayList<>();
        for (String category : cats) {
            if (!StringUtils.isBlank(category.trim())) {
                categories.add(category);
            }
        }
        return categories;
    }