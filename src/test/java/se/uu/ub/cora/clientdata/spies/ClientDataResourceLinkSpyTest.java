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
import java.util.Optional;
import java.util.function.Supplier;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientActionLink;
import se.uu.ub.cora.clientdata.ClientDataAttribute;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;
import se.uu.ub.cora.testutils.spies.MCRSpy;

public class ClientDataResourceLinkSpyTest {
	private static final String ADD_CALL = "addCall";
	private static final String ADD_CALL_AND_RETURN_FROM_MRV = "addCallAndReturnFromMRV";
	ClientDataResourceLinkSpy dataResourceLink;
	private MCRSpy MCRSpy;
	private MethodCallRecorder mcrForSpy;

	@BeforeMethod
	public void beforeMethod() {
		MCRSpy = new MCRSpy();
		mcrForSpy = MCRSpy.MCR;
		dataResourceLink = new ClientDataResourceLinkSpy();
	}

	@Test
	public void testMakeSureSpyHelpersAreSetUp() throws Exception {
		assertTrue(dataResourceLink.MCR instanceof MethodCallRecorder);
		assertTrue(dataResourceLink.MRV instanceof MethodReturnValues);
		assertSame(dataResourceLink.MCR.onlyForTestGetMRV(), dataResourceLink.MRV);
	}

	@Test
	public void testAddActionLink() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		ClientActionLink actionLink = new ClientActionLinkSpy();

		dataResourceLink.addActionLink(actionLink);

		mcrForSpy.assertParameter(ADD_CALL, 0, "actionLink", actionLink);
	}

	@Test
	public void testDefaultHasReadAction() throws Exception {
		assertFalse(dataResourceLink.hasReadAction());
	}

	@Test
	public void testHasReadAction() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> true);

		boolean retunedValue = dataResourceLink.hasReadAction();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaulGetActionLink() throws Exception {
		Optional<ClientActionLink> actionLink = dataResourceLink.getActionLink(ClientAction.READ);
		assertTrue(actionLink.isEmpty());
	}

	@Test
	public void testGetActionLink() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		ClientActionLink actionLink = new ClientActionLinkSpy();
		Optional<ClientActionLink> actionLinkOp = Optional.of(actionLink);

		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> actionLinkOp);

		Optional<ClientActionLink> retunedValue = dataResourceLink.getActionLink(ClientAction.READ);

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testSetRepeatId() throws Exception {
		dataResourceLink.MCR = MCRSpy;

		dataResourceLink.setRepeatId("repeat1");

		mcrForSpy.assertParameter(ADD_CALL, 0, "repeatId", "repeat1");
	}

	@Test
	public void testDefaultGetRepeatId() throws Exception {
		assertTrue(dataResourceLink.getRepeatId() instanceof String);
	}

	@Test
	public void testGetRepeatId() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataResourceLink.getRepeatId();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetNameInData() throws Exception {
		assertTrue(dataResourceLink.getNameInData() instanceof String);
	}

	@Test
	public void testGetNameInData() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataResourceLink.getNameInData();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testAddAttributeByIdWithValue() throws Exception {
		dataResourceLink.MCR = MCRSpy;

		dataResourceLink.addAttributeByIdWithValue("attribId", "attribValue");

		mcrForSpy.assertParameter(ADD_CALL, 0, "nameInData", "attribId");
		mcrForSpy.assertParameter(ADD_CALL, 0, "value", "attribValue");
	}

	@Test
	public void testDefaultHasAttributes() throws Exception {
		assertFalse(dataResourceLink.hasAttributes());
	}

	@Test
	public void testHasAttributes() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				(Supplier<Boolean>) () -> true);

		boolean retunedValue = dataResourceLink.hasAttributes();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, retunedValue);
	}

	@Test
	public void testDefaultGetAttribute() throws Exception {
		assertTrue(dataResourceLink.getAttribute("nameInData") instanceof ClientDataAttribute);
	}

	@Test
	public void testGetAttribute() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ClientDataAttributeSpy::new);

		var returnedValue = dataResourceLink.getAttribute("nameInData");

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "nameInData");
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetAttributes() throws Exception {
		assertTrue(dataResourceLink.getAttributes() instanceof Collection<ClientDataAttribute>);
	}

	@Test
	public void testGetAttributes() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				ArrayList<ClientDataAttribute>::new);

		var returnedValue = dataResourceLink.getAttributes();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testSetMimeType() throws Exception {
		dataResourceLink.MCR = MCRSpy;

		dataResourceLink.setMimeType("someMime");

		mcrForSpy.assertParameter(ADD_CALL, 0, "mimeType", "someMime");
	}

	@Test
	public void testDefaultGetMimeType() throws Exception {
		assertTrue(dataResourceLink.getMimeType() instanceof String);
	}

	@Test
	public void testGetMimeType() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, String::new);

		String returnedValue = dataResourceLink.getMimeType();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}

	@Test
	public void testDefaultGetAttributeValue() throws Exception {

		Optional<String> returnedValue = dataResourceLink.getAttributeValue("someNameInData");

		assertTrue(returnedValue.isEmpty());
	}

	@Test
	public void testGetAttributeValue() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV,
				() -> Optional.of("someValueToReturn"));

		Optional<String> returnedValue = dataResourceLink.getAttributeValue("someNameInData");

		assertTrue(returnedValue.isPresent());
		assertEquals(returnedValue.get(), "someValueToReturn");
		mcrForSpy.assertParameter(ADD_CALL_AND_RETURN_FROM_MRV, 0, "nameInData", "someNameInData");
	}

	@Test
	public void testDefaultHasRepeatId() throws Exception {
		assertFalse(dataResourceLink.hasRepeatId());
	}

	@Test
	public void testHasRepeatId() throws Exception {
		dataResourceLink.MCR = MCRSpy;
		MCRSpy.MRV.setDefaultReturnValuesSupplier(ADD_CALL_AND_RETURN_FROM_MRV, () -> true);

		boolean returnedValue = dataResourceLink.hasRepeatId();

		mcrForSpy.assertMethodWasCalled(ADD_CALL_AND_RETURN_FROM_MRV);
		mcrForSpy.assertReturn(ADD_CALL_AND_RETURN_FROM_MRV, 0, returnedValue);
	}
}
