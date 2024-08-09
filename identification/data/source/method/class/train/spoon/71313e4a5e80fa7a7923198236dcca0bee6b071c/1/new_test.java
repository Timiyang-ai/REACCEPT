	@Test
	public void getConstructor() throws Exception {
		final Factory build = build(Foo.class);
		final CtClass<?> foo = (CtClass<?>) build.Type().get(Foo.class);

		assertEquals(3, foo.getConstructors().size());

		CtTypeReference<Object> typeString = build.Code().createCtTypeReference(String.class);
		CtConstructor<?> constructor = foo.getConstructor(typeString);
		assertEquals(typeString, constructor.getParameters().get(0).getType());

		CtArrayTypeReference<Object> typeStringArray = build.Core().createArrayTypeReference();
		typeStringArray.setComponentType(typeString);
		constructor = foo.getConstructor(typeStringArray);
		assertEquals(typeStringArray, constructor.getParameters().get(0).getType());

		CtArrayTypeReference<Object> typeStringArrayArray = build.Core().createArrayTypeReference();
		typeStringArrayArray.setComponentType(typeStringArray);
		constructor = foo.getConstructor(typeStringArrayArray);
		assertEquals(typeStringArrayArray, constructor.getParameters().get(0).getType());

		// contract: one could add a type member that already exists (equals but not same) and modify it afterwards
		// this adds some flexibility for client code
		// see https://github.com/INRIA/spoon/issues/1862
		CtConstructor cons = foo.getConstructors().toArray(new CtConstructor[0])[0].clone();
		foo.addConstructor(cons);
		// as long as we have not changed the signature, getConstructors, which is based on signatures,
		// thinks there is one single constructor (and that's OK)
		assertEquals(3, foo.getConstructors().size());
		cons.addParameter(cons.getFactory().createParameter().setType(cons.getFactory().Type().OBJECT));
		// now that we have changed the signature we can call getConstructors safely
		assertEquals(4, foo.getConstructors().size());
		// we cloned the first constructor, so it has the same position, and comes before the 2nd and 3rd constructor
		assertSame(cons, foo.getTypeMembers().get(1));
		// the parent is set (the core problem described in the issue has been fixed)
		assertSame(foo, cons.getParent());

		// now we clone and reset the position
		CtConstructor cons2 = foo.getConstructors().toArray(new CtConstructor[0])[0].clone();
		cons2.setPosition(null);
		// adding the constructor, this time, without a position
		foo.addConstructor(cons2);
		// without position, it has been addded at the end
		assertSame(cons2, foo.getTypeMembers().get(4));
	}