public static <T> Future<T> async(Callable<T> callable) {
    	if(autoCheckException)checkExceptionWrapped();
    	ASyncFXCallable<T> call=new ASyncFXCallable<T>(callable,true);
    	executorService.submit((Runnable)call); //exception handling not guaranteed
    	return call;
    }