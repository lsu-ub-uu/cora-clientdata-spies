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

import java.util.Optional;

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientActionLink;
import se.uu.ub.cora.clientdata.ClientDataAuthentication;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

@SuppressWarnings("exports")
public class ClientDataAuthenticationSpy implements ClientDataAuthentication {
	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientDataAuthenticationSpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("getToken", () -> "someToken");
		MRV.setDefaultReturnValuesSupplier("getLoginId", () -> "someLoginId");
		MRV.setDefaultReturnValuesSupplier("getUserId", () -> "someUserId");
		MRV.setDefaultReturnValuesSupplier("getValidUntil", () -> "someValidUntil");
		MRV.setDefaultReturnValuesSupplier("getRenewUntil", () -> "someRenewUntil");
		MRV.setDefaultReturnValuesSupplier("getFirstName", () -> "someFirstName");
		MRV.setDefaultReturnValuesSupplier("getLastName", () -> "someLastName");
		MRV.setDefaultReturnValuesSupplier("getActionLink", Optional::empty);
	}

	@Override
	public String getToken() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getLoginId() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getUserId() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getValidUntil() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getRenewUntil() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getFirstName() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getLastName() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public Optional<ClientActionLink> getActionLink(ClientAction action) {
		return (Optional<ClientActionLink>) MCR.addCallAndReturnFromMRV("action", action);
	}
}
