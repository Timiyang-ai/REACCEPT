protected void internalClose() throws SQLException {
		closeAndClearResultSetHandles();

		this.internalStatement.close();

	}