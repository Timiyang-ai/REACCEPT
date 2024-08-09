public void getApplication(String applicationId,
                             final AsyncCallback<ApplicationDto> callback) {
    applicationRpcService.getApplication(applicationId,
        new DataCallback<ApplicationDto>(callback) {
          @Override
          protected void onResult(ApplicationDto result) {
          }
        });
  }