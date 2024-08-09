@Test
	public void testRelativeOrientation() throws IOException,
			StructureException {

		// Get points from a structure.
		Structure pdb = StructureIO.getStructure("4hhb.A");
		Point3d[] cloud = Calc.atomsToPoints(StructureTools
				.getRepresentativeAtomArray(pdb));
		Point3d[] cloud2 = CalcPoint.clonePoint3dArray(cloud);
		
		// Test orientation angle equal to 0 at this point
		double angle = UnitQuaternions.orientationAngle(cloud, cloud2, false);
		assertEquals(angle, 0, 0.001);
		
		// Apply a 30 degree rotation to cloud
		AxisAngle4d axis = new AxisAngle4d(new Vector3d(1,1,1), Math.PI / 6);
		Matrix4d transform = new Matrix4d();
		transform.set(axis);
		
		CalcPoint.transform(transform, cloud);
		angle = UnitQuaternions.orientationAngle(cloud, cloud2, false);
		
		// Test that angle was recovered
		assertEquals(angle, Math.PI / 6, 0.001);
	}