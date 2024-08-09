public static List getAllInterfaces(Class cls) {
        if (cls == null) {
            return null;
        }

        List interfacesFound = new ArrayList();
        getAllInterfaces(cls, interfacesFound);

        return interfacesFound;
    }