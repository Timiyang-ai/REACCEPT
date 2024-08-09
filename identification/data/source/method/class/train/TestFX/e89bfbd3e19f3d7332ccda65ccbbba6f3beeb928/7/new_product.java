public static Future<Void> async(Runnable runnable) {
    	if(autoCheckException)checkExceptionWrapped();
    	Callable<Void> call=new ASyncFXCallable<Void>(runnable, true);
    	return executorService.submit(call);
    }