public static String getGetterForInterface(String yangName, String returnType, boolean isList,
            int generatedJavaFiles) {

        if (!isList) {
            return getGetterInterfaceString(returnType, yangName, generatedJavaFiles);
        }
        String listAttr = getListString() + returnType + DIAMOND_CLOSE_BRACKET;
        return getGetterInterfaceString(listAttr, yangName, generatedJavaFiles);
    }