public static SelectionKeysSet optimize(Selector selector, ILogger logger) {
        checkNotNull(selector, "selector");
        checkNotNull(logger, "logger");

        try {
            SelectionKeysSet set = new SelectionKeysSet();

            Class<?> selectorImplClass = findOptimizableSelectorClass(selector);
            if (selectorImplClass == null) {
                return null;
            }

            Field selectedKeysField = selectorImplClass.getDeclaredField("selectedKeys");
            selectedKeysField.setAccessible(true);

            Field publicSelectedKeysField = selectorImplClass.getDeclaredField("publicSelectedKeys");
            publicSelectedKeysField.setAccessible(true);

            selectedKeysField.set(selector, set);
            publicSelectedKeysField.set(selector, set);

            logger.finest("Optimized Selector: " + selector.getClass().getName());
            return set;
        } catch (Throwable t) {
            // we don't want to print at warning level because it could very well be that the target JVM doesn't
            // support this optimization. That is why we print on finest
            logger.finest("Failed to optimize Selector: " + selector.getClass().getName(), t);
            return null;
        }
    }