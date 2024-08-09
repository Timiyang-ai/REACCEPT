public static String fromAttribute(final String name, final String prefix) {

        // If even one logical word grouping cannot be found, default to
        // simply converting the attribute to uppercase and adding the
        // prefix
        Matcher groupMatcher = ATTRIBUTE_NAME_GROUPING.matcher(name);
        if (!groupMatcher.find())
            return prefix + name.toUpperCase();

        // Split the given name into logical word groups, separated by
        // underscores and converted to uppercase
        StringBuilder builder = new StringBuilder(prefix);
        builder.append(groupMatcher.group(0).toUpperCase());

        while (groupMatcher.find()) {
            builder.append("_");
            builder.append(groupMatcher.group(0).toUpperCase());
        }

        return builder.toString();

    }