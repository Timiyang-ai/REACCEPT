public static String makeNamespaceFromClassName(String className, String protocol) {
        int index = className.lastIndexOf('.');

        if (index == -1) {
            return protocol + "://" + "DefaultNamespace";
        }

        String packageName = className.substring(0, index);

        StringTokenizer st = new StringTokenizer(packageName, ".");
        String[] words = new String[st.countTokens()];

        for (int i = words.length - 1; i >= 0; --i) {
            words[i] = st.nextToken();
        }

        return protocol + "://" + String.join(".", words) + "/";
    }