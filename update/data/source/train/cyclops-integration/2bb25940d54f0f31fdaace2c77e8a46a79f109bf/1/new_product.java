public static <T1>  AnyM<Stream<T1>> sequence(Stream<AnyM<T1>> seq){
			return AsGenericMonad.asMonad(Stream.of(1))
										.flatMap(in-> monad(seq.map(it->it.unwrap()))
												.flatten().unwrap())
												.anyM();
	}