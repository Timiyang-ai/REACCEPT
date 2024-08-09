public static void error(Object obj){
		if(obj instanceof Throwable){
			Throwable e = (Throwable)obj;
			error(e, e.getMessage());
		}else{
			error("{}", obj);
		}
	}