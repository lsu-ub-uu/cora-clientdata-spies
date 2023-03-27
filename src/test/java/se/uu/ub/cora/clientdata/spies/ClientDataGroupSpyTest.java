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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.clientdata.ClientDataAtomic;
import se.uu.ub.cora.clientdata.ClientDataAttribute;
import se.uu.ub.cora.clientdata.ClientDataChild;
import se.uu.ub.cora.clientdata.ClientDataChildFilter;
import se.uu.ub.cora.clientdata.ClientDataGroup;
import se.uu.ub.cora.clientdata.ClientDataRecordLink;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;
import se.uu.ub.cora.testutils.spies.MCRSpy;

public class ClientDataGroupSpyTest {
	private static final String ADD_CALL = "addCall";
	private static final String ADD_CALL_AND_RETURN_FROM_MRV = "addCallAndReturnFromMRV";
	ClientDataGroupSpy dataGroup;
	private MCRSpy MCRSpy;
	private MethodCallRecorder mcrForSpy;

	@BeforeMethod
	public void beforeMethod() {
		MCRSpy = new MCRSpy();
		mcrForSpy = MCRSpy.MCR;
		dataGroup = new ClientDataGroupSpy();
	}

	@Test
	public void testMakeSureSpyHelpersAreSetUp() throws Exception {
		assertTrue(dataGroup.MCR instanceof MethodCallRecorder);
		assertTrue(dataGroup.MRV instanceof MethodReturnValues);
		assertSame(dataGroup.MCR.onlyForTestGetMRV(), dataGroup.MRV);
	}

	@Test
	public void testAddChildNoSpy() throws Exception {
		ClientDataChildSpy clientDataChild = new ClientDataChildSpy();
		dataGroup.addChild(clientDataChild);

		dataGroup.MCR.assertParameter("addChild", 0, "dataChild", clientDataChild);
	}

	@Test
	public void testSetRepeatId() throws Exception {
		dataGroup.MCR = MCRSpy;

		dataGroup.setRepeatId("repeat1");

		mcrForSpy.assertParameter(ADD_CALL, 0, "repeatId", "repeat1");
	}

	@Test
	public void testDefaultGetRepeatId() throws Exception {
		assertTrue(dataGroup.getRepeatId() instanceof String);
	}

	@Test
	public void testGetRepeatId() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataGroup.getRepeatId();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testAddAttributeByIdWithValue() throws Exception {
		dataGroup.MCR = MCRSpy;

		dataGroup.addAttributeByIdWithValue("attribId", "attribValue");

		mcrForSpy.assertParameter(ADD_CALL, 0, "nameInData", "attribId");
		mcrForSpy.assertParameter(ADD_CALL, 0, "value", "attribValue");
	}

	@Test
	public void testDefaultHasAttributes() throws Exception {
		assertFalse(dataGroup.hasAttributes());
	}

	@Test
	public void testHasAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> true);

		boolean retunedValue = dataGroup.hasAttributes();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAttribute() throws Exception {
		assertTrue(dataGroup.getAttribute("nameInData") instanceof ClientDataAttribute);
	}

	@Test
	public void testGetAttribute() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataAttributeSpy::new);

		var returnedValue = dataGroup.getAttribute("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetAttributes() throws Exception {
		assertTrue(dataGroup.getAttributes() instanceof Collection<ClientDataAttribute>);
	}

	@Test
	public void testGetAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataAttribute>::new);

		var returnedValue = dataGroup.getAttributes();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetNameInData() throws Exception {
		assertTrue(dataGroup.getNameInData() instanceof String);
	}

	@Test
	public void testGetNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataGroup.getNameInData();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultHasChildren() throws Exception {
		assertTrue(dataGroup.hasChildren());
	}

	@Test
	public void testHasChildren() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> false);

		boolean retunedValue = dataGroup.hasChildren();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultContainsChildWithNameInData() throws Exception {
		assertFalse(dataGroup.containsChildWithNameInData("nameInData"));
	}

	@Test
	public void testContainsChildWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> false);

		boolean retunedValue = dataGroup.containsChildWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testAddChild() throws Exception {
		ClientDataChildSpy clientDataChild = new ClientDataChildSpy();
		dataGroup.MCR = MCRSpy;

		dataGroup.addChild(clientDataChild);

		mcrForSpy.assertParameter(ADD_CALL, 0, "dataChild", clientDataChild);
	}

	@Test
	public void testAddChildren() throws Exception {
		Collection<ClientDataChild> children = new ArrayList<>();
		dataGroup.MCR = MCRSpy;

		dataGroup.addChildren(children);

		mcrForSpy.assertParameter(ADD_CALL, 0, "dataChildren", children);
	}

	@Test
	public void testDefaultGetChildren() throws Exception {
		assertTrue(dataGroup.getChildren() instanceof List<ClientDataChild>);
	}

	@Test
	public void testGetChildren() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataChild>::new);

		List<ClientDataChild> retunedValue = dataGroup.getChildren();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAllChildrenWithNameInData() throws Exception {
		assertTrue(dataGroup
				.getAllChildrenWithNameInData("nameInData") instanceof List<ClientDataChild>);
	}

	@Test
	public void testGetAllChildrenWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataChild>::new);

		List<ClientDataChild> retunedValue = dataGroup.getAllChildrenWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAllChildrenWithNameInDataAndAttributes() throws Exception {
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();
		assertTrue(dataGroup.getAllChildrenWithNameInDataAndAttributes("nameInData",
				dataAttribute) instanceof List<ClientDataChild>);
	}

	@Test
	public void testGetAllChildrenWithNameInDataAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataChild>::new);
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();

		List<ClientDataChild> retunedValue = dataGroup
				.getAllChildrenWithNameInDataAndAttributes("nameInData", dataAttribute);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		Object[] returnValue = (Object[]) mcrForSpy
				.getValueForMethodNameAndCallNumberAndParameterName(ADD_CALL_AND_RETURN_FROM_MRV, 0,
						"childAttributes");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "childAttributes", returnValue);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetFirstChildWithNameInData() throws Exception {
		assertTrue(
				dataGroup.getFirstChildWithNameInData("nameInData") instanceof ClientDataChildSpy);
	}

	@Test
	public void testGetFirstChildWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataChildSpy::new);

		ClientDataChild retunedValue = dataGroup.getFirstChildWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetFirstAtomicValueWithNameInData() throws Exception {
		assertTrue(dataGroup.getFirstAtomicValueWithNameInData("nameInData") instanceof String);
	}

	@Test
	public void testGetFirstAtomicValueWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String retunedValue = dataGroup.getFirstAtomicValueWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	// TODO
	@Test
	public void testDefaultGetFirstDataAtomicWithNameInData() throws Exception {
		assertTrue(dataGroup
				.getFirstDataAtomicWithNameInData("nameInData") instanceof ClientDataAtomicSpy);
	}

	@Test
	public void testGetFirstDataAtomicWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataAtomicSpy::new);

		ClientDataChild retunedValue = dataGroup.getFirstDataAtomicWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAllDataAtomicsWithNameInData() throws Exception {
		assertTrue(dataGroup
				.getAllDataAtomicsWithNameInData("nameInData") instanceof List<ClientDataAtomic>);
	}

	@Test
	public void testGetAllDataAtomicsWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataAtomic>::new);

		List<ClientDataAtomic> retunedValue = dataGroup
				.getAllDataAtomicsWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAllDataAtomicsWithNameInDataAndAttributes() throws Exception {
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();
		assertTrue(dataGroup.getAllDataAtomicsWithNameInDataAndAttributes("nameInData",
				dataAttribute) instanceof List<ClientDataAtomic>);
	}

	@Test
	public void testGetAllDataAtomicsWithNameInDataAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataAtomic>::new);
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();

		List<ClientDataAtomic> retunedValue = (List<ClientDataAtomic>) dataGroup
				.getAllDataAtomicsWithNameInDataAndAttributes("nameInData", dataAttribute);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		Object[] returnValue = (Object[]) mcrForSpy
				.getValueForMethodNameAndCallNumberAndParameterName(ADD_CALL_AND_RETURN_FROM_MRV, 0,
						"childAttributes");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "childAttributes", returnValue);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetFirstGroupWithNameInData() throws Exception {
		assertTrue(
				dataGroup.getFirstGroupWithNameInData("nameInData") instanceof ClientDataGroupSpy);
	}

	@Test
	public void testGetFirstGroupWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataGroupSpy::new);

		ClientDataGroup retunedValue = dataGroup.getFirstGroupWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAllGroupsWithNameInData() throws Exception {
		assertTrue(dataGroup
				.getAllGroupsWithNameInData("nameInData") instanceof Collection<ClientDataGroup>);
	}

	@Test
	public void testGetAllGroupsWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataAtomic>::new);

		List<ClientDataGroup> retunedValue = dataGroup.getAllGroupsWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAllGroupsWithNameInDataAndAttributes() throws Exception {
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();
		assertTrue(dataGroup.getAllGroupsWithNameInDataAndAttributes("nameInData",
				dataAttribute) instanceof Collection<ClientDataGroup>);
	}

	@Test
	public void testGetAllGroupsWithNameInDataAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataAtomic>::new);
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();

		Collection<ClientDataGroup> retunedValue = dataGroup
				.getAllGroupsWithNameInDataAndAttributes("nameInData", dataAttribute);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		Object[] returnValue = (Object[]) mcrForSpy
				.getValueForMethodNameAndCallNumberAndParameterName(ADD_CALL_AND_RETURN_FROM_MRV, 0,
						"childAttributes");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "childAttributes", returnValue);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultRemoveFirstChildWithNameInData() throws Exception {
		assertTrue(dataGroup.removeFirstChildWithNameInData("nameInData"));
	}

	@Test
	public void testRemoveFirstChildWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> false);

		boolean retunedValue = dataGroup.removeFirstChildWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultRemoveAllChildrenWithNameInData() throws Exception {
		assertTrue(dataGroup.removeAllChildrenWithNameInData("nameInData"));
	}

	@Test
	public void testRemoveAllChildrenWithNameInData() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> false);

		boolean retunedValue = dataGroup.removeAllChildrenWithNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultRemoveAllChildrenWithNameInDataAndAttributes() throws Exception {
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();
		assertTrue(dataGroup.removeAllChildrenWithNameInDataAndAttributes("nameInData",
				dataAttribute));
	}

	@Test
	public void testRemoveAllChildrenWithNameInDataAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> false);
		ClientDataAttribute dataAttribute = new ClientDataAttributeSpy();

		boolean retunedValue = dataGroup.removeAllChildrenWithNameInDataAndAttributes("nameInData",
				dataAttribute);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		Object[] returnValue = (Object[]) mcrForSpy
				.getValueForMethodNameAndCallNumberAndParameterName(ADD_CALL_AND_RETURN_FROM_MRV, 0,
						"childAttributes");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "childAttributes", returnValue);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAllChildrenMatchingFilter() throws Exception {
		ClientDataChildFilter childFilter = new ClientDataChildFilterSpy();
		assertTrue(dataGroup
				.getAllChildrenMatchingFilter(childFilter) instanceof List<ClientDataChild>);
	}

	@Test
	public void testGetAllChildrenMatchingFilter() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataChildFilter>::new);
		ClientDataChildFilter childFilter = new ClientDataChildFilterSpy();

		List<ClientDataChild> retunedValue = dataGroup.getAllChildrenMatchingFilter(childFilter);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "childFilter", childFilter);

		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultRemoveAllChildrenMatchingFilter() throws Exception {
		ClientDataChildFilter childFilter = new ClientDataChildFilterSpy();
		assertTrue(dataGroup.removeAllChildrenMatchingFilter(childFilter));
	}

	@Test
	public void testRemoveAllChildrenMatchingFilter() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> false);
		ClientDataChildFilter childFilter = new ClientDataChildFilterSpy();

		boolean retunedValue = dataGroup.removeAllChildrenMatchingFilter(childFilter);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "childFilter", childFilter);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetFirstChildOfTypeWithNameAndAttributes() throws Exception {
		Class<ClientDataChild> type = ClientDataChild.class;
		String name = "name";

		ClientDataChild returnedValue = dataGroup.getFirstChildOfTypeAndName(type, name);

		assertTrue(returnedValue instanceof ClientDataChild);
	}

	@Test
	public void testGetFirstChildOfTypeWithNameAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataRecordLinkSpy::new);

		Class<ClientDataRecordLinkSpy> type = ClientDataRecordLinkSpy.class;
		String name = "name";

		ClientDataRecordLink returnedValue = dataGroup.getFirstChildOfTypeAndName(type, name);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "type", type);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "name", name);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);

	}

	@Test
	public void testDefaultGetChildrenOfTypeWithNameAndAttributes() throws Exception {
		Class<ClientDataChild> type = ClientDataChild.class;
		String name = "name";

		List<ClientDataChild> returnedValue = dataGroup.getChildrenOfTypeAndName(type, name);

		assertTrue(returnedValue instanceof List<ClientDataChild>);
	}

	@Test
	public void testGetChildrenOfTypeWithNameAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;
		List<ClientDataRecordLinkSpy> listOfLinks = List.of(new ClientDataRecordLinkSpy());

		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> listOfLinks);

		Class<ClientDataRecordLinkSpy> type = ClientDataRecordLinkSpy.class;
		String name = "name";

		List<ClientDataRecordLinkSpy> returnedValue = dataGroup.getChildrenOfTypeAndName(type,
				name);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "type", type);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "name", name);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultRemoveFirstChildWithTypeNameAndAttributes() throws Exception {
		Class<ClientDataChild> type = ClientDataChild.class;
		String name = "name";

		boolean returnedValue = dataGroup.removeFirstChildWithTypeAndName(type, name);

		assertFalse(returnedValue);
	}

	@Test
	public void testRemoveFirstChildWithTypeNameAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;

		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> true);

		Class<ClientDataRecordLinkSpy> type = ClientDataRecordLinkSpy.class;
		String name = "name";

		boolean returnedValue = dataGroup.removeFirstChildWithTypeAndName(type, name);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "type", type);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "name", name);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultRemoveChildrenWithTypeNameAndAttributes() throws Exception {
		Class<ClientDataChild> type = ClientDataChild.class;
		String name = "name";

		boolean returnedValue = dataGroup.removeChildrenWithTypeAndName(type, name);

		assertFalse(returnedValue);
	}

	@Test
	public void testRemoveFirstChildrenWithTypeNameAndAttributes() throws Exception {
		dataGroup.MCR = MCRSpy;

		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> true);

		Class<ClientDataRecordLinkSpy> type = ClientDataRecordLinkSpy.class;
		String name = "name";

		boolean returnedValue = dataGroup.removeChildrenWithTypeAndName(type, name);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "type", type);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "name", name);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetAttributeValue() throws Exception {

		Optional<String> returnedValue = dataGroup.getAttributeValue("someNameInData");

		assertTrue(returnedValue.isEmpty());
	}

	@Test
	public void testGetAttributeValue() throws Exception {
		dataGroup.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				() -> Optional.of("someValueToReturn"));

		Optional<String> returnedValue = dataGroup.getAttributeValue("someNameInData");

		assertTrue(returnedValue.isPresent());
		assertEquals(returnedValue.get(), "someValueToReturn");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "someNameInData");
	}
}
