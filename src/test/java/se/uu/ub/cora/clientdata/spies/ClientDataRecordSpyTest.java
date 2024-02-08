/*
 * Copyright 2022 Olov McKie
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
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientDataRecordGroup;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;
import se.uu.ub.cora.testutils.spies.MCRSpy;

public class ClientDataRecordSpyTest {
	private static final String ADD_CALL = "addCall";
	private static final String ADD_CALL_AND_RETURN_FROM_MRV = "addCallAndReturnFromMRV";
	ClientDataRecordSpy dataRecord;
	private MCRSpy MCRSpy;
	private MethodCallRecorder mcrForSpy;

	@BeforeMethod
	public void beforeMethod() {
		MCRSpy = new MCRSpy();
		mcrForSpy = MCRSpy.MCR;
		dataRecord = new ClientDataRecordSpy();
	}

	@Test
	public void testMakeSureSpyHelpersAreSetUp() throws Exception {
		assertTrue(dataRecord.MCR instanceof MethodCallRecorder);
		assertTrue(dataRecord.MRV instanceof MethodReturnValues);
		assertSame(dataRecord.MCR.onlyForTestGetMRV(), dataRecord.MRV);
	}

	@Test
	public void testDefaultGetType() throws Exception {
		assertTrue(dataRecord.getType() instanceof String);
	}

	@Test
	public void testGetType() throws Exception {
		dataRecord.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataRecord.getType();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetId() throws Exception {
		assertTrue(dataRecord.getId() instanceof String);
	}

	@Test
	public void testGetId() throws Exception {
		dataRecord.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataRecord.getId();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testSetDataGroup() throws Exception {
		dataRecord.MCR = MCRSpy;
		ClientDataRecordGroupSpy dataRecordGroup = new ClientDataRecordGroupSpy();

		dataRecord.setDataRecordGroup(dataRecordGroup);

		mcrForSpy.assertParameter(ADD_CALL, 0, "data" + "Group", dataRecordGroup);
	}

	@Test
	public void testDefaultGetDataGroup() throws Exception {
		assertTrue(dataRecord.getDataRecordGroup() instanceof ClientDataRecordGroup);
	}

	@Test
	public void testGetDataGroup() throws Exception {
		dataRecord.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataRecordGroupSpy::new);

		var returnedValue = dataRecord.getDataRecordGroup();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testAddActionLink() throws Exception {
		dataRecord.MCR = MCRSpy;
		ClientActionLinkSpy someAction = new ClientActionLinkSpy();

		dataRecord.addActionLink(someAction);

		mcrForSpy.assertParameter(ADD_CALL, 0, "actionLink", someAction);
	}

	@Test
	public void testDefaultGetActionLink() throws Exception {
		assertTrue(dataRecord.getActionLink(ClientAction.CREATE).isEmpty());
	}

	@Test
	public void testGetActionLink() throws Exception {
		dataRecord.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, Optional::empty);

		var returnedValue = dataRecord.getActionLink(ClientAction.CREATE);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testAddReadPermission() throws Exception {
		dataRecord.MCR = MCRSpy;

		dataRecord.addReadPermission("aReadPermission");

		mcrForSpy.assertParameter(ADD_CALL, 0, "readPermission", "aReadPermission");
	}

	@Test
	public void testAddReadPermissions() throws Exception {
		dataRecord.MCR = MCRSpy;

		Collection<String> readPermissions = Set.of("aReadPermission", "anotherReadPermission");
		dataRecord.addReadPermissions(readPermissions);

		mcrForSpy.assertParameter(ADD_CALL, 0, "readPermissions", readPermissions);
	}

	@Test
	public void testDefaultHasReadPermissions() throws Exception {

		assertFalse(dataRecord.hasReadPermissions());
	}

	@Test
	public void testHasReadPermissions() throws Exception {
		dataRecord.MCR = MCRSpy;

		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> true);

		boolean returnedValue = dataRecord.hasReadPermissions();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetReadPermissions() throws Exception {

		Set<String> returnedValue = dataRecord.getReadPermissions();

		assertTrue(returnedValue.isEmpty());
	}

	@Test
	public void testGetReadPermissions() throws Exception {
		dataRecord.MCR = MCRSpy;
		Set<String> readPermissions = Set.of("aReadPermission", "anotherReadPermission");
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				() -> readPermissions);

		Set<String> returnedValue = dataRecord.getReadPermissions();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testAddWritePermission() throws Exception {
		dataRecord.MCR = MCRSpy;

		dataRecord.addWritePermission("aWritePermission");

		mcrForSpy.assertParameter(ADD_CALL, 0, "writePermission", "aWritePermission");
	}

	@Test
	public void testAddWritePermissions() throws Exception {
		dataRecord.MCR = MCRSpy;

		Collection<String> writePermissions = Set.of("aWritePermission", "anotherReadPermission");
		dataRecord.addWritePermissions(writePermissions);

		mcrForSpy.assertParameter(ADD_CALL, 0, "writePermissions", writePermissions);
	}

	@Test
	public void testDefaultHasWritePermissions() throws Exception {
		assertFalse(dataRecord.hasWritePermissions());
	}

	@Test
	public void testHasWritePermissions() throws Exception {

		dataRecord.MCR = MCRSpy;

		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> true);

		boolean returnedValue = dataRecord.hasWritePermissions();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetWritePermissions() throws Exception {
		Set<String> returnedValue = dataRecord.getWritePermissions();

		assertTrue(returnedValue.isEmpty());
	}

	@Test
	public void testGetWritePermissions() throws Exception {
		dataRecord.MCR = MCRSpy;
		Set<String> writePermissions = Set.of("aWritePermission", "anotherWritePermission");
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				() -> writePermissions);

		Set<String> returnedValue = dataRecord.getWritePermissions();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetSearchId() throws Exception {
		assertTrue(dataRecord.getSearchId() instanceof String);
	}

	@Test
	public void testGetSearchId() throws Exception {
		dataRecord.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataRecord.getSearchId();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultHasProtocol() throws Exception {
		assertFalse(dataRecord.hasProtocol("aProtocol"));
	}

	@Test
	public void testHasProtocol() throws Exception {
		dataRecord.MCR = MCRSpy;

		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> true);

		boolean returnedValue = dataRecord.hasProtocol("someProtocol");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameters(ADD_CALL_AND_RETURN_FROM_MRV, 0, "someProtocol");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testPutProtocol() throws Exception {
		dataRecord.MCR = MCRSpy;

		Map<String, String> protocolProperties = Collections.emptyMap();

		dataRecord.putProtocol("someProtocol", protocolProperties);

		mcrForSpy.assertMethodWasCalled(ADD_CALL);
		mcrForSpy.assertParameters(ADD_CALL, 0, "someProtocol", protocolProperties);
	}

	@Test
	public void testDefaultGetProtocol() throws Exception {
		assertEquals(dataRecord.getProtocol("nonExistingProtocol"), Collections.emptyMap());
	}

	@Test
	public void testName() throws Exception {
		dataRecord.MCR = MCRSpy;

		Map<String, String> properties = new HashMap<>();
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> properties);

		var returnedValue = dataRecord.getProtocol("someProtocol");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameters(ADD_CALL_AND_RETURN_FROM_MRV, 0, "someProtocol");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}
}
