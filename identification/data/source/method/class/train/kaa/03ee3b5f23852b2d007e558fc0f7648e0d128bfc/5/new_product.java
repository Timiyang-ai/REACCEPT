public void getTenant(String tenantId,
                        final AsyncCallback<TenantDto> callback) {
    tenantRpcService.getTenant(tenantId,
        new DataCallback<TenantDto>(callback) {
          @Override
          protected void onResult(TenantDto result) {
          }
        });
  }