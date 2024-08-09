@Deprecated
	@Override
	public Objectify transactionless() {
		return transactor.transactionless(this);
	}