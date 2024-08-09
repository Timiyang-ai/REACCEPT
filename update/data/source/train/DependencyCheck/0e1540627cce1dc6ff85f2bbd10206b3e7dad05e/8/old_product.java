public static synchronized String getCweName(String cweId) {
        if (cweId != null) {
            return CWE.get(cweId);
        }
        return null;
    }