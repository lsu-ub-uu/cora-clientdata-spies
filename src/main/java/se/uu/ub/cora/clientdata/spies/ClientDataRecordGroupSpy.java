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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import se.uu.ub.cora.clientdata.ClientDataAtomic;
import se.uu.ub.cora.clientdata.ClientDataAttribute;
import se.uu.ub.cora.clientdata.ClientDataChild;
import se.uu.ub.cora.clientdata.ClientDataChildFilter;
import se.uu.ub.cora.clientdata.ClientDataGroup;
import se.uu.ub.cora.clientdata.ClientDataRecordGroup;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

@SuppressWarnings("exports")
public class ClientDataRecordGroupSpy implements ClientDataRecordGroup {
	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientDataRecordGroupSpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("getNameInData", String::new);
		MRV.setDefaultReturnValuesSupplier("hasAttributes", (Supplier<Boolean>) () -> false);
		MRV.setDefaultReturnValuesSupplier("getAttribute", ClientDataAttributeSpy::new);
		MRV.setDefaultReturnValuesSupplier("getAttributes", ArrayList<ClientDataAttribute>::new);
		MRV.setDefaultReturnValuesSupplier("hasChildren", (Supplier<Boolean>) () -> true);
		MRV.setDefaultReturnValuesSupplier("containsChildWithNameInData",
				(Supplier<Boolean>) () -> false);
		MRV.setDefaultReturnValuesSupplier("getChildren", ArrayList<ClientDataChild>::new);
		MRV.setDefaultReturnValuesSupplier("getAllChildrenWithNameInData",
				ArrayList<ClientDataChild>::new);
		MRV.setDefaultReturnValuesSupplier("getAllChildrenWithNameInDataAndAttributes",
				ArrayList<ClientDataChild>::new);
		MRV.setDefaultReturnValuesSupplier("getFirstChildWithNameInData", ClientDataChildSpy::new);
		MRV.setDefaultReturnValuesSupplier("getFirstAtomicValueWithNameInData", String::new);
		MRV.setDefaultReturnValuesSupplier("getFirstDataAtomicWithNameInData",
				ClientDataAtomicSpy::new);
		MRV.setDefaultReturnValuesSupplier("getAllDataAtomicsWithNameInData",
				ArrayList<ClientDataAtomic>::new);
		MRV.setDefaultReturnValuesSupplier("getAllDataAtomicsWithNameInDataAndAttributes",
				ArrayList<ClientDataAtomic>::new);
		MRV.setDefaultReturnValuesSupplier("getFirstGroupWithNameInData", ClientDataGroupSpy::new);
		MRV.setDefaultReturnValuesSupplier("getAllGroupsWithNameInData",
				ArrayList<ClientDataGroup>::new);
		MRV.setDefaultReturnValuesSupplier("getAllGroupsWithNameInDataAndAttributes",
				ArrayList<ClientDataGroup>::new);
		MRV.setDefaultReturnValuesSupplier("removeFirstChildWithNameInData",
				(Supplier<Boolean>) () -> true);
		MRV.setDefaultReturnValuesSupplier("removeAllChildrenWithNameInData",
				(Supplier<Boolean>) () -> true);
		MRV.setDefaultReturnValuesSupplier("removeAllChildrenWithNameInDataAndAttributes",
				(Supplier<Boolean>) () -> true);
		MRV.setDefaultReturnValuesSupplier("getAllChildrenMatchingFilter",
				ArrayList<ClientDataChild>::new);
		MRV.setDefaultReturnValuesSupplier("removeAllChildrenMatchingFilter",
				(Supplier<Boolean>) () -> true);

		MRV.setDefaultReturnValuesSupplier("getType", String::new);
		MRV.setDefaultReturnValuesSupplier("getId", String::new);
		MRV.setDefaultReturnValuesSupplier("getDataDivider", String::new);
	}

	@Override
	public void addAttributeByIdWithValue(String nameInData, String value) {
		MCR.addCall("nameInData", nameInData, "value", value);
	}

	@Override
	public boolean hasAttributes() {
		return (boolean) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public ClientDataAttribute getAttribute(String nameInData) {
		return (ClientDataAttribute) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ClientDataAttribute> getAttributes() {
		return (Collection<ClientDataAttribute>) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String getNameInData() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public boolean hasChildren() {
		return (boolean) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public boolean containsChildWithNameInData(String nameInData) {
		return (boolean) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public void addChild(ClientDataChild dataChild) {
		MCR.addCall("dataChild", dataChild);
	}

	@Override
	public void addChildren(Collection<ClientDataChild> dataChildren) {
		MCR.addCall("dataChildren", dataChildren);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientDataChild> getChildren() {
		return (List<ClientDataChild>) MCR.addCallAndReturnFromMRV();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientDataChild> getAllChildrenWithNameInData(String nameInData) {
		return (List<ClientDataChild>) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientDataChild> getAllChildrenWithNameInDataAndAttributes(String nameInData,
			ClientDataAttribute... childAttributes) {
		return (List<ClientDataChild>) MCR.addCallAndReturnFromMRV("nameInData", nameInData,
				"childAttributes", childAttributes);
	}

	@Override
	public ClientDataChild getFirstChildWithNameInData(String nameInData) {
		return (ClientDataChild) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public String getFirstAtomicValueWithNameInData(String nameInData) {
		return (String) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public ClientDataAtomic getFirstDataAtomicWithNameInData(String nameInData) {
		return (ClientDataAtomic) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientDataAtomic> getAllDataAtomicsWithNameInData(String nameInData) {
		return (List<ClientDataAtomic>) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ClientDataAtomic> getAllDataAtomicsWithNameInDataAndAttributes(
			String nameInData, ClientDataAttribute... childAttributes) {
		return (Collection<ClientDataAtomic>) MCR.addCallAndReturnFromMRV("nameInData", nameInData,
				"childAttributes", childAttributes);
	}

	@Override
	public ClientDataGroup getFirstGroupWithNameInData(String nameInData) {
		return (ClientDataGroup) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientDataGroup> getAllGroupsWithNameInData(String nameInData) {
		return (List<ClientDataGroup>) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ClientDataGroup> getAllGroupsWithNameInDataAndAttributes(String nameInData,
			ClientDataAttribute... childAttributes) {
		return (Collection<ClientDataGroup>) MCR.addCallAndReturnFromMRV("nameInData", nameInData,
				"childAttributes", childAttributes);
	}

	@Override
	public boolean removeFirstChildWithNameInData(String nameInData) {
		return (boolean) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public boolean removeAllChildrenWithNameInData(String nameInData) {
		return (boolean) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public boolean removeAllChildrenWithNameInDataAndAttributes(String nameInData,
			ClientDataAttribute... childAttributes) {
		return (boolean) MCR.addCallAndReturnFromMRV("nameInData", nameInData, "childAttributes",
				childAttributes);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientDataChild> getAllChildrenMatchingFilter(ClientDataChildFilter childFilter) {
		return (List<ClientDataChild>) MCR.addCallAndReturnFromMRV("childFilter", childFilter);
	}

	@Override
	public boolean removeAllChildrenMatchingFilter(ClientDataChildFilter childFilter) {
		return (boolean) MCR.addCallAndReturnFromMRV("childFilter", childFilter);
	}

	@Override
	public String getType() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setType(String type) {
		MCR.addCall("type", type);
	}

	@Override
	public String getId() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setId(String id) {
		MCR.addCall("id", id);
	}

	@Override
	public String getDataDivider() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public void setDataDivider(String dataDivider) {
		MCR.addCall("dataDivider", dataDivider);
	}

}
