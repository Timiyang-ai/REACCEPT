@PublicAPI(usage = ACCESS)
    public static String ensureSimpleName(String name) {
        int lastIndexOfDot = name.lastIndexOf('.');
        String partAfterDot = lastIndexOfDot >= 0 ? name.substring(lastIndexOfDot + 1) : name;

        int lastIndexOf$ = partAfterDot.lastIndexOf('$');
        String simpleNameCandidate = lastIndexOf$ >= 0 ? partAfterDot.substring(lastIndexOf$ + 1) : partAfterDot;

        for (int i = 0; i < simpleNameCandidate.length(); i++) {
            if (Character.isJavaIdentifierStart(simpleNameCandidate.charAt(i))) {
                return simpleNameCandidate.substring(i);
            }
        }
        return "";
    }