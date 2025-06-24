/*
 * Copyright 2022, 2023 Olov McKie
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

import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientActionLink;
import se.uu.ub.cora.clientdata.ClientDataAtomic;
import se.uu.ub.cora.clientdata.ClientDataAttribute;
import se.uu.ub.cora.clientdata.ClientDataChildFilter;
import se.uu.ub.cora.clientdata.ClientDataGroup;
import se.uu.ub.cora.clientdata.ClientDataList;
import se.uu.ub.cora.clientdata.ClientDataRecord;
import se.uu.ub.cora.clientdata.ClientDataRecordGroup;
import se.uu.ub.cora.clientdata.ClientDataRecordLink;
import se.uu.ub.cora.clientdata.ClientDataResourceLink;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;
import se.uu.ub.cora.testutils.spies.MCRSpy;

public class ClientDataFactorySpyTest {
	private static final String ADD_CALL = "addCall";
	private static final String ADD_CALL_AND_RETURN_FROM_MRV = "addCallAndReturnFromMRV";
	ClientDataFactorySpy dataFactory;
	private MCRSpy MCRSpy;
	private MethodCallRecorder mcrForSpy;

	@BeforeMethod
	public void beforeMethod() {
		MCRSpy = new MCRSpy();
		mcrForSpy = MCRSpy.MCR;
		dataFactory = new ClientDataFactorySpy();
	}

	@Test
	public void testMakeSureSpyHelpersAreSetUp() {
		assertTrue(dataFactory.MCR instanceof MethodCallRecorder);
		assertTrue(dataFactory.MRV instanceof MethodReturnValues);
		assertSame(dataFactory.MCR.onlyForTestGetMRV(), dataFactory.MRV);
	}

	@Test
	public void testDefaultFactorListUsingNameOfDataType() {
		assertTrue(dataFactory
				.factorListUsingNameOfDataType("nameOfDataType") instanceof ClientDataListSpy);
	}

	@Test
	public void testFactorListUsingNameOfDataType() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataListSpy::new);

		ClientDataList retunedValue = dataFactory.factorListUsingNameOfDataType("nameOfDataType");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameOfDataType",
				"nameOfDataType");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorRecordUsingDataGroup() {
		ClientDataRecordGroup dataRecordGroupSpy = new ClientDataRecordGroupSpy();
		assertTrue(dataFactory.factorRecordUsingDataRecordGroup(
				dataRecordGroupSpy) instanceof ClientDataRecordSpy);
	}

	@Test
	public void testFactorRecordUsingDataGroup() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataRecordSpy::new);
		ClientDataRecordGroupSpy dataGroupSpy = new ClientDataRecordGroupSpy();

		ClientDataRecord retunedValue = dataFactory.factorRecordUsingDataRecordGroup(dataGroupSpy);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "dataGroup", dataGroupSpy);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorRecordGroupUsingNameInData() {
		assertTrue(dataFactory.factorRecordGroupUsingNameInData(
				"nameInData") instanceof ClientDataRecordGroupSpy);
	}

	@Test
	public void testFactorRecordGroupUsingNameInData() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataRecordGroupSpy::new);

		ClientDataRecordGroup retunedValue = dataFactory
				.factorRecordGroupUsingNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorRecordGroupFromDataGroup() {
		ClientDataGroup dataGroupSpy = new ClientDataGroupSpy();
		assertTrue(dataFactory
				.factorRecordGroupFromDataGroup(dataGroupSpy) instanceof ClientDataRecordGroupSpy);
	}

	@Test
	public void testFactorRecordGroupFromDataGroup() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataRecordGroupSpy::new);
		ClientDataGroup dataGroupSpy = new ClientDataGroupSpy();

		ClientDataRecordGroup retunedValue = dataFactory
				.factorRecordGroupFromDataGroup(dataGroupSpy);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "dataGroup", dataGroupSpy);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorGroupFromDataRecordGroup() {
		ClientDataRecordGroup dataRecordGroupSpy = new ClientDataRecordGroupSpy();
		assertTrue(dataFactory
				.factorGroupFromDataRecordGroup(dataRecordGroupSpy) instanceof ClientDataGroupSpy);
	}

	@Test
	public void testFactorGroupFromDataRecordGroup() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataGroupSpy::new);
		ClientDataRecordGroup dataRecordGroupSpy = new ClientDataRecordGroupSpy();

		ClientDataGroup retunedValue = dataFactory
				.factorGroupFromDataRecordGroup(dataRecordGroupSpy);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "dataRecordGroup",
				dataRecordGroupSpy);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorGroupUsingNameInData() {
		assertTrue(
				dataFactory.factorGroupUsingNameInData("nameInData") instanceof ClientDataGroupSpy);
	}

	@Test
	public void testFactorGroupUsingNameInData() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataGroupSpy::new);

		ClientDataGroup retunedValue = dataFactory.factorGroupUsingNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorRecordLinkUsingNameInData() {
		assertTrue(dataFactory
				.factorRecordLinkUsingNameInData("nameInData") instanceof ClientDataRecordLinkSpy);
	}

	@Test
	public void testFactorRecordLinkUsingNameInData() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataRecordLinkSpy::new);

		ClientDataRecordLink retunedValue = dataFactory
				.factorRecordLinkUsingNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorRecordLinkUsingNameInDataAndTypeAndId() {
		assertTrue(dataFactory.factorRecordLinkUsingNameInDataAndTypeAndId("nameInData", "type",
				"id") instanceof ClientDataRecordLinkSpy);
	}

	@Test
	public void testFactorRecordLinkUsingNameInDataAndTypeAndId() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataRecordLinkSpy::new);

		ClientDataRecordLink retunedValue = dataFactory
				.factorRecordLinkUsingNameInDataAndTypeAndId("nameInData", "type", "id");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "recordType", "type");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "recordId", "id");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorResourceLinkUsingNameInData() {
		assertTrue(
				dataFactory.factorResourceLinkUsingNameInDataAndTypeAndIdAndMimeType("nameInData",
						"someType", "someId", "mimeType") instanceof ClientDataResourceLinkSpy);
	}

	@Test
	public void testFactorResourceLinkUsingNameInData() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataResourceLinkSpy::new);

		ClientDataResourceLink retunedValue = dataFactory
				.factorResourceLinkUsingNameInDataAndTypeAndIdAndMimeType("nameInData", "someType",
						"someId", "mimeType");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "recordType", "someType");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "recordId", "someId");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "mimeType", "mimeType");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorAtomicUsingNameInDataAndValue() {
		assertTrue(dataFactory.factorAtomicUsingNameInDataAndValue("nameInData",
				"value") instanceof ClientDataAtomicSpy);
	}

	@Test
	public void testFactorAtomicUsingNameInDataAndValue() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataAtomicSpy::new);

		ClientDataAtomic retunedValue = dataFactory
				.factorAtomicUsingNameInDataAndValue("nameInData", "value");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "value", "value");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorAtomicUsingNameInDataAndValueAndRepeatId() {
		assertTrue(dataFactory.factorAtomicUsingNameInDataAndValueAndRepeatId("nameInData", "value",
				"repeatId") instanceof ClientDataAtomicSpy);
	}

	@Test
	public void testFactorAtomicUsingNameInDataAndValueAndRepeatId() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataAtomicSpy::new);

		ClientDataAtomic retunedValue = dataFactory
				.factorAtomicUsingNameInDataAndValueAndRepeatId("nameInData", "value", "repeatId");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "value", "value");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "repeatId", "repeatId");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorAttributeUsingNameInDataAndValue() {
		assertTrue(dataFactory.factorAttributeUsingNameInDataAndValue("nameInData",
				"value") instanceof ClientDataAttributeSpy);
	}

	@Test
	public void testFactorAttributeUsingNameInDataAndValue() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataAttributeSpy::new);

		ClientDataAttribute retunedValue = dataFactory
				.factorAttributeUsingNameInDataAndValue("nameInData", "value");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "value", "value");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorActionLinkUsingAction() {
		assertTrue(dataFactory
				.factorActionLinkUsingAction(ClientAction.CREATE) instanceof ClientActionLink);
	}

	@Test
	public void testFactorActionLinkUsingAction() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientActionLinkSpy::new);

		ClientActionLink retunedValue = dataFactory.factorActionLinkUsingAction(ClientAction.READ);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "clientAction",
				ClientAction.READ);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultFactorDataChildFilterUsingNameInData() {
		assertTrue(dataFactory.factorDataChildFilterUsingNameInData(
				"nameInData") instanceof ClientDataChildFilterSpy);
	}

	@Test
	public void testFactorDataChildFilterUsingNameInData() {
		dataFactory.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataChildFilterSpy::new);

		ClientDataChildFilter retunedValue = dataFactory
				.factorDataChildFilterUsingNameInData("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "childNameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}
}
