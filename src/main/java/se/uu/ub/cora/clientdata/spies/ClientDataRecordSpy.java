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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientActionLink;
import se.uu.ub.cora.clientdata.ClientDataGroup;
import se.uu.ub.cora.clientdata.ClientDataRecord;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

@SuppressWarnings("exports")
public class ClientDataRecordSpy implements ClientDataRecord {
	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientDataRecordSpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("getType", String::new);
		MRV.setDefaultReturnValuesSupplier("getId", String::new);
		MRV.setDefaultReturnValuesSupplier("getDataGroup", ClientDataGroupSpy::new);
		MRV.setDefaultReturnValuesSupplier("getActionLink", Optional::empty);
		MRV.setDefaultReturnValuesSupplier("hasReadPermissions", (Supplier<Boolean>) () -> false);
		MRV.setDefaultReturnValuesSupplier("getReadPermissions", Collections::emptySet);
		MRV.setDefaultReturnValuesSupplier("hasWritePermissions", (Supplier<Boolean>) () -> false);
		MRV.setDefaultReturnValuesSupplier("getWritePermissions", Collections::emptySet);
		MRV.setDefaultReturnValuesSupplier("getSearchId", String::new);
	}

	@Override
	public String getType() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getId() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setDataGroup(ClientDataGroup dataGroup) {
		MCR.addCall("dataGroup", dataGroup);

	}

	@Override
	public ClientDataGroup getDataGroup() {
		return (ClientDataGroup) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void addActionLink(ClientActionLink actionLink) {
		MCR.addCall("actionLink", actionLink);
	}

	@Override
	public Optional<ClientActionLink> getActionLink(ClientAction action) {
		return (Optional<ClientActionLink>) MCR.addCallAndReturnFromMRV("action", action);
	}

	@Override
	public void addReadPermission(String readPermission) {
		MCR.addCall("readPermission", readPermission);
	}

	@Override
	public void addReadPermissions(Collection<String> readPermissions) {
		MCR.addCall("readPermissions", readPermissions);
	}

	@Override
	public Set<String> getReadPermissions() {
		return (Set<String>) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public boolean hasReadPermissions() {
		return (boolean) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void addWritePermission(String writePermission) {
		MCR.addCall("writePermission", writePermission);

	}

	@Override
	public void addWritePermissions(Collection<String> writePermissions) {
		MCR.addCall("writePermissions", writePermissions);

	}

	@Override
	public Set<String> getWritePermissions() {
		return (Set<String>) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public boolean hasWritePermissions() {
		return (boolean) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getSearchId() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

}
