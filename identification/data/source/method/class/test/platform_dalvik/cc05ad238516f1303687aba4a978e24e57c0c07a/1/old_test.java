@TestInfo(
      level = TestLevel.PARTIAL_OK,
      purpose = "Verifies that getPolicyTree method returns the root node of " +
            "the valid policy tree.",
      targets = {
        @TestTarget(
          methodName = "getPolicyTree",
          methodArgs = {}
        )
    })
    public final void testGetPolicyTree01() throws Exception {
        TrustAnchor ta = TestUtils.getTrustAnchor();
        if (ta == null) {
            fail(getName() + ": not performed (could not create test TrustAnchor)");
        }

        // valid policy tree case;
        PolicyNode pn = TestUtils.getPolicyTree();
        PKIXCertPathValidatorResult vr =
            new PKIXCertPathValidatorResult(
                    ta,
                    pn,
                    testPublicKey);

        // must return the same reference passed
        // as a parameter to the constructor
        assertSame(pn, vr.getPolicyTree());
    }