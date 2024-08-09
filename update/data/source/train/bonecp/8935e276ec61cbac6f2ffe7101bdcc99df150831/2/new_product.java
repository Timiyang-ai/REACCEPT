protected void internalClose() throws SQLException {
		closeAndClearResultSetHandles();
		this.batchSQL = new StringBuffer();
		this.internalStatement.close();

	}