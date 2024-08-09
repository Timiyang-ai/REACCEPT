public static String getSetterForClass(JavaAttributeInfo attr, String className) {

        String attrQuaifiedType = getReturnType(attr);
        String attributeName = getSmallCase(attr.getAttributeName());
        if (!attr.isListAttr()) {
            return getSetter(className, attributeName, attrQuaifiedType);
        }
        String listAttr = getListString() + attrQuaifiedType + DIAMOND_CLOSE_BRACKET;
        return getSetter(className, attributeName, listAttr);
    }