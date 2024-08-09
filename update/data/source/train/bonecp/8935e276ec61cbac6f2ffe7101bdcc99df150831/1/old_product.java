protected void internalClose() throws SQLException {
		clearResultSetHandles(true);

		this.internalStatement.close();

	}