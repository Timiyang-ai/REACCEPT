protected String getParamValue(String name) {
        AdminCommand unwrappedCommand = getUnwrappedCommand();
        Class<?> commandClass = unwrappedCommand.getClass(); 
        for (final Field field : commandClass.getDeclaredFields()) {
            Param param = field.getAnnotation(Param.class);
            if (param != null && name.equals(CommandModel.getParamName(param, field))) {
                try {
                    AccessController.doPrivileged(new PrivilegedAction<Object>() {

                        @Override
                        public Object run() {
                            field.setAccessible(true);
                            return null;
                        }
                    });
                    return (String) field.get(unwrappedCommand);
                } catch (IllegalAccessException e) {
                	throw new RuntimeException("Unexpected error", e);
                }
            }
        }
        return null;
    }