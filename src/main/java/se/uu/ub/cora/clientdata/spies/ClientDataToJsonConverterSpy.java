package se.uu.ub.cora.clientdata.spies;

import se.uu.ub.cora.clientdata.converter.ClientDataToJsonConverter;
import se.uu.ub.cora.json.builder.JsonObjectBuilder;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

public class ClientDataToJsonConverterSpy implements ClientDataToJsonConverter {

	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientDataToJsonConverterSpy() {
		MCR.useMRV(MRV);

		MRV.setDefaultReturnValuesSupplier("toJsonObjectBuilder", null);
		MRV.setDefaultReturnValuesSupplier("toJsonCompactFormat",
				ClientDataToJsonConverterSpy::new);
		MRV.setDefaultReturnValuesSupplier("toJson", ClientDataToJsonConverterSpy::new);
	}

	@Override
	public JsonObjectBuilder toJsonObjectBuilder() {
		return (JsonObjectBuilder) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String toJsonCompactFormat() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

	@Override
	public String toJson() {
		return (String) MCR.addCallAndReturnFromMRV();
	}

}
