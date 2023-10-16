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

import se.uu.ub.cora.clientdata.ClientAction;
import se.uu.ub.cora.clientdata.ClientActionLink;
import se.uu.ub.cora.clientdata.ClientDataAtomic;
import se.uu.ub.cora.clientdata.ClientDataAttribute;
import se.uu.ub.cora.clientdata.ClientDataChildFilter;
import se.uu.ub.cora.clientdata.ClientDataFactory;
import se.uu.ub.cora.clientdata.ClientDataGroup;
import se.uu.ub.cora.clientdata.ClientDataList;
import se.uu.ub.cora.clientdata.ClientDataRecord;
import se.uu.ub.cora.clientdata.ClientDataRecordGroup;
import se.uu.ub.cora.clientdata.ClientDataRecordLink;
import se.uu.ub.cora.clientdata.ClientDataResourceLink;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

@SuppressWarnings("exports")
public class ClientDataFactorySpy implements ClientDataFactory {

	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientDataFactorySpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("factorListUsingNameOfDataType", ClientDataListSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorRecordUsingDataRecordGroup",
				ClientDataRecordSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorRecordGroupUsingNameInData",
				ClientDataRecordGroupSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorRecordGroupFromDataGroup",
				ClientDataRecordGroupSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorGroupFromDataRecordGroup",
				ClientDataGroupSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorGroupUsingNameInData", ClientDataGroupSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorRecordLinkUsingNameInData",
				ClientDataRecordLinkSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorRecordLinkUsingNameInDataAndTypeAndId",
				ClientDataRecordLinkSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorResourceLinkUsingNameInData",
				ClientDataResourceLinkSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorAtomicUsingNameInDataAndValue",
				ClientDataAtomicSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorAtomicUsingNameInDataAndValueAndRepeatId",
				ClientDataAtomicSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorAttributeUsingNameInDataAndValue",
				ClientDataAttributeSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorActionLinkUsingAction", ClientActionLinkSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorDataChildFilterUsingNameInData",
				ClientDataChildFilterSpy::new);
	}

	@Override
	public ClientDataList factorListUsingNameOfDataType(String nameOfDataType) {
		return (ClientDataList) MCR.addCallAndReturnFromMRV("nameOfDataType", nameOfDataType);
	}

	@Override
	public ClientDataRecord factorRecordUsingDataRecordGroup(
			ClientDataRecordGroup dataRecordGroup) {
		return (ClientDataRecord) MCR.addCallAndReturnFromMRV("dataGroup", dataRecordGroup);
	}

	@Override
	public ClientDataRecordGroup factorRecordGroupUsingNameInData(String nameInData) {
		return (ClientDataRecordGroup) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public ClientDataRecordGroup factorRecordGroupFromDataGroup(ClientDataGroup dataGroup) {
		return (ClientDataRecordGroup) MCR.addCallAndReturnFromMRV("dataGroup", dataGroup);
	}

	@Override
	public ClientDataGroup factorGroupFromDataRecordGroup(ClientDataRecordGroup dataRecordGroup) {
		return (ClientDataGroup) MCR.addCallAndReturnFromMRV("dataRecordGroup", dataRecordGroup);
	}

	@Override
	public ClientDataGroup factorGroupUsingNameInData(String nameInData) {
		return (ClientDataGroup) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public ClientDataRecordLink factorRecordLinkUsingNameInData(String nameInData) {
		return (ClientDataRecordLink) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public ClientDataRecordLink factorRecordLinkUsingNameInDataAndTypeAndId(String nameInData,
			String recordType, String recordId) {
		return (ClientDataRecordLink) MCR.addCallAndReturnFromMRV("nameInData", nameInData,
				"recordType", recordType, "recordId", recordId);
	}

	@Override
	public ClientDataResourceLink factorResourceLinkUsingNameInData(String nameInData) {
		return (ClientDataResourceLink) MCR.addCallAndReturnFromMRV("nameInData", nameInData);
	}

	@Override
	public ClientDataAtomic factorAtomicUsingNameInDataAndValue(String nameInData, String value) {
		return (ClientDataAtomic) MCR.addCallAndReturnFromMRV("nameInData", nameInData, "value",
				value);
	}

	@Override
	public ClientDataAtomic factorAtomicUsingNameInDataAndValueAndRepeatId(String nameInData,
			String value, String repeatId) {
		return (ClientDataAtomic) MCR.addCallAndReturnFromMRV("nameInData", nameInData, "value",
				value, "repeatId", repeatId);
	}

	@Override
	public ClientDataAttribute factorAttributeUsingNameInDataAndValue(String nameInData,
			String value) {
		return (ClientDataAttribute) MCR.addCallAndReturnFromMRV("nameInData", nameInData, "value",
				value);
	}

	@Override
	public ClientActionLink factorActionLinkUsingAction(ClientAction clientAction) {
		return (ClientActionLink) MCR.addCallAndReturnFromMRV("clientAction", clientAction);
	}

	@Override
	public ClientDataChildFilter factorDataChildFilterUsingNameInData(String childNameInData) {
		return (ClientDataChildFilter) MCR.addCallAndReturnFromMRV("childNameInData",
				childNameInData);
	}

}
