public static String getGetterForClass(JavaAttributeInfo attr) {

        String attrQuaifiedType = getReturnType(attr);
        String attributeName = getSmallCase(attr.getAttributeName());

        if (!attr.isListAttr()) {
            return getGetter(attrQuaifiedType, attributeName);
        }
        String listAttr = getListString() + attrQuaifiedType + DIAMOND_CLOSE_BRACKET;
        return getGetter(listAttr, attributeName);
    }