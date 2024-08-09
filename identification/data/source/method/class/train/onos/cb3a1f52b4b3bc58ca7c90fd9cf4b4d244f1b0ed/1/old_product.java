public static String getGetterForInterface(String yangName, String returnType, boolean isList) {

        if (!isList) {
            return getGetterInterfaceString(returnType, yangName);
        }
        String listAttr = getListString() + returnType + DIAMOND_CLOSE_BRACKET;
        return getGetterInterfaceString(listAttr, yangName);
    }