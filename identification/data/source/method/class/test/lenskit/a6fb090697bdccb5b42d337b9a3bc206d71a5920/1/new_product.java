protected abstract ScoredLongList recommend(long user, SparseVector ratings, int n,
			@Nullable LongSet candidates, @Nonnull LongSet exclude);