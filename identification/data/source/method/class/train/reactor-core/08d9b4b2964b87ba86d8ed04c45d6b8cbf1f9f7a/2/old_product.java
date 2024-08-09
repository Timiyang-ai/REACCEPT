public static <I> Flux<I> wrap(Publisher<? extends I> source){
		if(source instanceof Fuseable){
			return onAssembly(new FuseableFluxSource<>(source));
		}
		return onAssembly(new FluxSource<>(source));
	}