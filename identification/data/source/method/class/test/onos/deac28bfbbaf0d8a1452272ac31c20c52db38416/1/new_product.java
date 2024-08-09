public static String getRootPackage(byte version, String nameSpace, Date revision,
                                        YangToJavaNamingConflictUtil conflictResolver) {

        String pkg;
        pkg = DEFAULT_BASE_PKG;
        pkg = pkg + PERIOD;
        pkg = pkg + getYangVersion(version);
        pkg = pkg + PERIOD;
        pkg = pkg + getPkgFromNameSpace(nameSpace, conflictResolver);
        pkg = pkg + PERIOD;
        pkg = pkg + getYangRevisionStr(revision);

        return pkg.toLowerCase();
    }