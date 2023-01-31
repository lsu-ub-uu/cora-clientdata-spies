package se.uu.ub.cora.clientdata.spies;

import se.uu.ub.cora.clientdata.ClientConvertible;
import se.uu.ub.cora.clientdata.converter.ClientDataToJsonConverter;
import se.uu.ub.cora.clientdata.converter.ClientDataToJsonConverterFactory;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

public class ClientDataToJsonConverterFactorySpy implements ClientDataToJsonConverterFactory {
	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientDataToJsonConverterFactorySpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("factorUsingConvertible",
				ClientDataToJsonConverterSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorUsingBaseUrlAndConvertible",
				ClientDataToJsonConverterSpy::new);
		MRV.setDefaultReturnValuesSupplier("factorUsingBaseUrlAndRecordUrlAndConvertible",
				ClientDataToJsonConverterSpy::new);
	}

	@Override
	public ClientDataToJsonConverter factorUsingConvertible(ClientConvertible convertible) {
		return (ClientDataToJsonConverter) MCR.addCallAndReturnFromMRV("convertible", convertible);
	}

	@Override
	public ClientDataToJsonConverter factorUsingBaseUrlAndConvertible(String baseUrl,
			ClientConvertible convertible) {
		return (ClientDataToJsonConverter) MCR.addCallAndReturnFromMRV("baseUrl", baseUrl,
				"convertible", convertible);
	}

	@Override
	public ClientDataToJsonConverter factorUsingBaseUrlAndRecordUrlAndConvertible(String baseUrl,
			String recordUrl, ClientConvertible convertible) {
		return (ClientDataToJsonConverter) MCR.addCallAndReturnFromMRV("baseUrl", baseUrl,
				"recordUrl", recordUrl, "convertible", convertible);
	}

}
