protected abstract List<ScoredId> recommend(long user, SparseVector ratings, int n,
			@Nullable LongSet candidates, @Nonnull LongSet exclude);