public final boolean addDelegateComponents(int childIndex,
                    Collection<AbstractComponent> childComponents) {

        if (isLeaf()) {
            throw new UnsupportedOperationException("components declared as leaf cannot be mutated");
        }
        Platform platform = PlatformAccess.getPlatform();
        PolicyManager policyManager = platform.getPolicyManager();
        PolicyContext context = new PolicyContext();
        context.setProperty(PolicyContext.PropertyName.TARGET_COMPONENT.getName(), this);
        context.setProperty(PolicyContext.PropertyName.ACTION.getName(), 'w');
        context
                        .setProperty(PolicyContext.PropertyName.SOURCE_COMPONENTS.getName(),
                                        childComponents);
        if (policyManager.execute(PolicyInfo.CategoryType.ACCEPT_DELEGATE_MODEL_CATEGORY.getKey(),
                        context).getStatus()) {
          
            if (!childComponents.isEmpty()) {
                for (AbstractComponent childComponent : childComponents) {
                    processAddDelegateComponent(childIndex, childComponent);
                }
            }
            
            addDelegateComponentsCallback(childComponents);
            return true;

        }
        return false;
    }