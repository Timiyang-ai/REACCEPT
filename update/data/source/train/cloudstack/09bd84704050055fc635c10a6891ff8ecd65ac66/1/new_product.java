protected void setupPriorityOfRedundantRouter() {
        if (this.isRedundant() && this.routersNeedReset()) {
            for (final DomainRouterVO router : this.routers) {
                // getUpdatedPriority() would update the value later
                router.setPriority(0);
                router.setIsPriorityBumpUp(false);
                routerDao.update(router.getId(), router);
            }
        }
    }