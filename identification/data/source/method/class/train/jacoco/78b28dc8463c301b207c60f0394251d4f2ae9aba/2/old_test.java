	@Test
	public void skipNonOpcodes() {
		m.visitFrame(Opcodes.F_FULL, 0, null, 0, null);
		final Label label = new Label();
		m.visitLabel(label);
		m.visitLineNumber(42, label);
		m.visitInsn(Opcodes.NOP);

		// should skip all non opcodes
		matcher.cursor = m.instructions.getFirst();
		matcher.skipNonOpcodes();
		assertSame(m.instructions.getLast(), matcher.cursor);

		// should not change cursor when it points on instruction with opcode
		matcher.skipNonOpcodes();
		assertSame(m.instructions.getLast(), matcher.cursor);

		// should not do anything when cursor is null
		matcher.cursor = null;
		matcher.skipNonOpcodes();
	}