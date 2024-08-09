public static CachedFileHandle createSourceFiles(String pkg, String yangName, int types)
            throws IOException {
        yangName = JavaIdentifierSyntax.getCamelCase(yangName);
        CachedFileHandle handler = new CachedJavaFileHandle(pkg, yangName, types);

        return handler;
    }