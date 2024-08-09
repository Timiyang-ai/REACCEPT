public Builder enabledBlockTypes(Set<Class<? extends Block>> enabledBlockTypes) {
            if (enabledBlockTypes == null) {
                throw new NullPointerException("enabledBlockTypes must not be null");
            }
            this.enabledBlockTypes = enabledBlockTypes;
            return this;
        }