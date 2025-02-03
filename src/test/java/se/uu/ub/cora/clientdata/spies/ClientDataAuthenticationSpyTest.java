/*
 * Copyright 2025 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.clientdata.spies;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.Optional;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientDataAuthentication;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;
import se.uu.ub.cora.testutils.spies.MCRSpy;

public class ClientDataAuthenticationSpyTest {

	private static final String ADD_CALL_AND_RETURN_FROM_MRV = "addCallAndReturnFromMRV";
	ClientDataAuthenticationSpy dataAuthentication;
	private MCRSpy MCRSpy;
	private MethodCallRecorder mcrForSpy;

	@BeforeMethod
	public void beforeMethod() {
		MCRSpy = new MCRSpy();
		mcrForSpy = MCRSpy.MCR;
		dataAuthentication = new ClientDataAuthenticationSpy();
	}

	@Test
	public void testMakeSureSpyHelpersAreSetUp() {
		assertTrue(dataAuthentication.MCR instanceof MethodCallRecorder);
		assertTrue(dataAuthentication.MRV instanceof MethodReturnValues);
		assertSame(dataAuthentication.MCR.onlyForTestGetMRV(), dataAuthentication.MRV);
	}

	@Test
	public void testInstanceOfClientDataAuthentication() {
		assertTrue(dataAuthentication instanceof ClientDataAuthentication);
	}

	@Test
	public void testDefaultGetToken() {
		assertEquals(dataAuthentication.getToken(), "someToken");
	}

	@Test
	public void testGetToken() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataAuthentication.getToken();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetLoginId() {
		assertEquals(dataAuthentication.getLoginId(), "someLoginId");
	}

	@Test
	public void testGetLoginId() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataAuthentication.getLoginId();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetUserId() {
		assertEquals(dataAuthentication.getUserId(), "someUserId");
	}

	@Test
	public void testGetUserId() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataAuthentication.getUserId();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetValidUntil() {
		assertEquals(dataAuthentication.getValidUntil(), "someValidUntil");
	}

	@Test
	public void testGetValidUntil() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataAuthentication.getValidUntil();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetRenewUntil() {
		assertEquals(dataAuthentication.getRenewUntil(), "someRenewUntil");
	}

	@Test
	public void testGetRenewUntil() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataAuthentication.getRenewUntil();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetFirstName() {
		assertEquals(dataAuthentication.getFirstName(), "someFirstName");
	}

	@Test
	public void testGetFirstName() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataAuthentication.getFirstName();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetLastName() {
		assertEquals(dataAuthentication.getLastName(), "someLastName");
	}

	@Test
	public void testGetLastName() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataAuthentication.getLastName();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetActionLink() {
		assertTrue(dataAuthentication.getActionLink(ClientAction.CREATE).isEmpty());
	}

	@Test
	public void testGetActionLink() {
		dataAuthentication.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, Optional::empty);

		var returnedValue = dataAuthentication.getActionLink(ClientAction.CREATE);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameters("addCallAndReturnFromMRV", 0, ClientAction.CREATE);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}
}
