private static String getCanonicalName(String className) {
        className = StringUtils.deleteWhitespace(className);
        if (className == null) {
            return null;
        } else {
            int dim = 0;
            while (className.startsWith("[")) {
                dim++;
                className = className.substring(1);
            }
            if (dim < 1) {
                return className;
            } else {
                if (className.startsWith("L")) {
                    className = className.substring(
                        1,
                        className.endsWith(";")
                            ? className.length() - 1
                            : className.length());
                } else {
                    if (className.length() > 0) {
                        className = reverseAbbreviationMap.get(className.substring(0, 1));
                    }
                }
                StringBuffer canonicalClassNameBuffer = new StringBuffer(className);
                for (int i = 0; i < dim; i++) {
                    canonicalClassNameBuffer.append("[]");
                }
                return canonicalClassNameBuffer.toString();
            }
        }
    }