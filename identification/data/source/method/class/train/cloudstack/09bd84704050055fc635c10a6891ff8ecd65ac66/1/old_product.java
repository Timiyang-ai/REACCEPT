protected void setupPriorityOfRedundantRouter() {
        if (isRedundant && routersNeedReset()) {
            for (final DomainRouterVO router : routers) {
                // getUpdatedPriority() would update the value later
                router.setPriority(0);
                router.setIsPriorityBumpUp(false);
                routerDao.update(router.getId(), router);
            }
        }
    }