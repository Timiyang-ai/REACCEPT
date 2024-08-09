@PublicAPI(usage = ACCESS)
    public static String ensureSimpleName(String name) {
        int innerClassStart = name.lastIndexOf('$');
        int classStart = name.lastIndexOf('.');
        if (innerClassStart < 0 && classStart < 0) {
            return name;
        }

        String lastPart = innerClassStart >= 0 ? name.substring(innerClassStart + 1) : name.substring(classStart + 1);
        return isAnonymousRest(lastPart) ? "" : lastPart;
    }