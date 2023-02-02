/*
 * Copyright 2023 Uppsala University Library
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

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientActionLink;
import se.uu.ub.cora.clientdata.ClientDataGroup;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

public class ClientActionLinkSpy implements ClientActionLink {

	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientActionLinkSpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("getAction", () -> ClientAction.READ);
		MRV.setDefaultReturnValuesSupplier("getURL", String::new);
		MRV.setDefaultReturnValuesSupplier("getRequestMethod", String::new);
		MRV.setDefaultReturnValuesSupplier("getAccept", String::new);
		MRV.setDefaultReturnValuesSupplier("getContentType", String::new);
		MRV.setDefaultReturnValuesSupplier("getBody", ClientDataGroupSpy::new);
	}

	@Override
	public ClientAction getAction() {
		return (ClientAction) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setURL(String url) {
		MCR.addCall("url", url);
	}

	@Override
	public String getURL() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setRequestMethod(String requestMethod) {
		MCR.addCall("requestMethod", requestMethod);

	}

	@Override
	public String getRequestMethod() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setAccept(String accept) {
		MCR.addCall("accept", accept);

	}

	@Override
	public String getAccept() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setContentType(String contentType) {
		MCR.addCall("contentType", contentType);
	}

	@Override
	public String getContentType() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setBody(ClientDataGroup body) {
		MCR.addCall("body", body);
	}

	@Override
	public ClientDataGroup getBody() {
		return (ClientDataGroup) MCR.addCallAndReturnFromMRV();
	}

}
