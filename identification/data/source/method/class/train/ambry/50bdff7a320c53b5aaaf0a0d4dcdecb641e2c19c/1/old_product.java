@Override
  public void onCompletion(Long result, Exception exception) {
    if (httpContent != null) {
      ReferenceCountUtil.release(httpContent);
    }
    callbackWrapper.updateBytesRead(result);
    if (exception != null || isLast) {
      callbackWrapper.invokeCallback(exception);
    }
  }