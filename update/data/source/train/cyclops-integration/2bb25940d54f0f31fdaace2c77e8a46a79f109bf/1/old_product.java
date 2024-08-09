public static <MONAD,T>  Monad<MONAD,T> sequence(Class c, List<?> seq){
		return (Monad)AsGenericMonad.asMonad(new ComprehenderSelector().selectComprehender(c).of(1))
				.flatMap(in-> asMonad(seq.stream()).flatMap(m-> m).unwrap()
							);
	}