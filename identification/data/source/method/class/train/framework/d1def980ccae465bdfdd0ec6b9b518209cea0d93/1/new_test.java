    protected <TYPE extends Enum<TYPE>> void createSelectAction(String caption,
            String category, Class<TYPE> enumType, TYPE initialValue,
            com.vaadin.tests.components.ComponentTestCase.Command<T, TYPE> command) {
        LinkedHashMap<String, TYPE> options = new LinkedHashMap<>();
        for (TYPE value : EnumSet.allOf(enumType)) {
            options.put(value.toString(), value);
        }
        createSelectAction(caption, category, options, initialValue.toString(),
                command);
    }