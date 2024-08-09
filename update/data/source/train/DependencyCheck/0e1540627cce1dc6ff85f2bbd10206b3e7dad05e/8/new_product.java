public static synchronized String getName(String cweId) {
        if (cweId != null) {
            return CWE.get(cweId);
        }
        return null;
    }