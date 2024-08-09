public final boolean addDelegateComponents(int childIndex,
                    Collection<AbstractComponent> childComponents) {
        DaoStrategy<AbstractComponent, ? extends DaoObject> daoStrategy = getDaoStrategy();

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
            List<AbstractComponent> sharedComponents = new ArrayList<AbstractComponent>();
            if (!isShared() || lockManager.isLockedForAllUsers(getId())
                            || lockManager.isExtendedLocking(getId()) || this
                            .isVersionedComponent()) {
                boolean retry = false;
                int maxRetries = 100;
                do {
                    try {
                        java.util.List<AbstractComponent> persistedChildComponents = new LinkedList<AbstractComponent>();
                        for (AbstractComponent childComponent : childComponents) {
                            if (shouldBeShare()) {
                                shareComponent(childComponent, this.getId(), false, sharedComponents);
                            }
                            persistedChildComponents.add(childComponent);
                        }
                        
                        // ensure children are persisted as they may not be unlocked
                        for (AbstractComponent c:sharedComponents) {
                            if (!childComponents.contains(c)) {
                                DaoStrategy<AbstractComponent, ? extends DaoObject> strategy = c.getDaoStrategy();
                                if (strategy != null) {
                                    strategy.saveObject();
                                }
                            }
                        } 
                        
                        if (daoStrategy != null) {
                            addDelegateComponentsBeforeSave(persistedChildComponents);
                            daoStrategy.saveObjects(childIndex, persistedChildComponents);
                        }
                        retry = false;
                    } catch (OptimisticLockException e) {
                        LOGGER.debug("optimistic lock problem, trying transaction again");
                        // reset components that were initially shared to ensure sharing will persist
                        for (AbstractComponent componentShared:sharedComponents) {
                            Updatable updater = componentShared.getCapability(Updatable.class);
                            if (updater != null) {
                                updater.setShared(false);
                            }
                        }
                        // if there was an optimistic lock exception then update all the components and retry. Everything needs to be merged as the problem could have
                        // been in the shared or this component. 
                        PlatformAccess.getPlatform().getPersistenceService().updateComponentsFromDatabase();
                        // for each shared component, reset the shared status
                        sharedComponents.clear();
                        retry = lockManager.isLockedForAllUsers(getId()) && maxRetries-- > 0;
                        if (!retry) {
                            LOGGER.debug("attempted to retry optimistic lock than the maximum number of retries, rethrowing exception");
                            throw e;
                        }
                    }
                } while (retry);
                
            }

            // adjust locks after the transaction has been committed, this is just a local operation
            LockManager lm = GlobalContext.getGlobalContext().getLockManager();
            for (AbstractComponent sharedComponent:sharedComponents) {
                lm.shareLock(sharedComponent.getComponentId());
            }
            
            if (childComponents.isEmpty()) {
                refreshViewManifestations();
            } else {
                originalAddDelegateComponents(childIndex, childComponents);
                refreshManifestationFromInsert(childIndex, childComponents);
            }

            addDelegateComponentsCallback(childComponents);
            return true;

        }
        addDelegateComponentsCallback(childComponents);
        return false;
    }