public static String getRootPackage(byte version, String nameSpace, String revision) {

        String pkg;
        pkg = DEFAULT_BASE_PKG;
        pkg = pkg + PERIOD;
        pkg = pkg + getYangVersion(version);
        pkg = pkg + PERIOD;
        pkg = pkg + getPkgFromNameSpace(nameSpace);
        pkg = pkg + PERIOD;
        pkg = pkg + getYangRevisionStr(revision);

        return pkg.toLowerCase();
    }