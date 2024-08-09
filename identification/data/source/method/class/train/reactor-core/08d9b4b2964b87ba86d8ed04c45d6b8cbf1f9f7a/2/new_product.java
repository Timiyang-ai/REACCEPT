public static <I> Flux<I> wrap(Publisher<? extends I> source){
		if(source instanceof Fuseable){
			return new FuseableFluxSource<>(source);
		}
		return new FluxSource<>(source);
	}