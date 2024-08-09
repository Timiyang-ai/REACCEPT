public void getTenant(String tenantId,
            final AsyncCallback<TenantUserDto> callback) {
        rpcService.getTenant(tenantId,
                new DataCallback<TenantUserDto>(callback) {
                    @Override
                    protected void onResult(TenantUserDto result) {
                    }
                });
    }