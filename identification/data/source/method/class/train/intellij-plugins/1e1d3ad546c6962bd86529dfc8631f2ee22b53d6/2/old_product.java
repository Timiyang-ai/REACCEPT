public static Map<String, Object> getClassProperties(IJavaClassType javaClassType) {
        if (javaClassType == null) {
            return new HashMap<String, Object>();
        }

        Collection<IJavaMethod> allPublicMethods = javaClassType.getPublicMethods(true);
        Map<String, Object> getterMethods = new HashMap<String, Object>();

        for (IJavaMethod method : allPublicMethods) {

            String nameProperty = method.getName();

            if (method.getReturnType() != null && (nameProperty.startsWith("get") || (nameProperty.startsWith("is") && method.getReturnType().getName().equals("boolean")))) {

                if (nameProperty.startsWith("get")) {
                    nameProperty = (nameProperty.replaceFirst("get", ""));
                } else {
                    nameProperty = (nameProperty.replaceFirst("is", ""));
                }

                if (!nameProperty.equals("")) {
                    nameProperty = StringUtils.uncapitalize(nameProperty);

                    getterMethods.put(nameProperty, method);
                }
            }
        }

        Map<String, IJavaField> allFields = javaClassType.getFields(true);
        Map<String, Object> propertyFields = new HashMap<String, Object>();

        for (Map.Entry<String, IJavaField> field : allFields.entrySet()) {
            if (field.getValue().getAnnotations().containsKey(PROPERTY_ANNOTATION)) {
                IJavaAnnotation annotation = field.getValue().getAnnotations().get(PROPERTY_ANNOTATION);

                if (annotation.getParameters().containsKey("read") && annotation.getParameters().get("read")[0].equals("false"))
                    continue;

                propertyFields.put(getName(field.getKey()), field.getValue());
            }
        }

        getterMethods.putAll(propertyFields);

        return getterMethods;
    }