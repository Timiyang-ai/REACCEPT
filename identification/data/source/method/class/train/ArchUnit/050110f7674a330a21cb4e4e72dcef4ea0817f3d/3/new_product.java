@PublicAPI(usage = ACCESS)
    public static String ensureSimpleName(String name) {
        int mostNestedClassStart = name.lastIndexOf('$');
        if (mostNestedClassStart > -1) {
            String lastPart = name.substring(mostNestedClassStart + 1);
            // lastPart contains "1" for anonymous classes, "NestedClass" for nested classes, and "1LocalClass" for local classes

            int javaIdentifierStart = indexOfJavaIdentifierStart(lastPart);
            if (javaIdentifierStart > -1) {
                return lastPart.substring(javaIdentifierStart);
            } else {
                // name is an anonymous class
                // return empty string, because java.lang.Class.getSimpleName() also returns empty string
                return "";
            }
        }

        int classStart = name.lastIndexOf('.');
        if (classStart > -1) {
            return name.substring(classStart + 1);
        }

        // name may be a class in default package or may be empty
        return name;
    }