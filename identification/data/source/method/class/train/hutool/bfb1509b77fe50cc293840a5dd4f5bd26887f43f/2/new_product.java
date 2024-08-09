public static void log(Object obj){
		if(obj instanceof Throwable){
			Throwable e = (Throwable)obj;
			log(e, e.getMessage());
		}else{
			log("{}", obj);
		}
	}