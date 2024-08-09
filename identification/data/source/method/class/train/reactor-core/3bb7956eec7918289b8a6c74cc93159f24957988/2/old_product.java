public final Flux<@NonNull T> sorted(Comparator<? super @NonNull T> comparator) {
		return sorted(comparator, 16);
	}