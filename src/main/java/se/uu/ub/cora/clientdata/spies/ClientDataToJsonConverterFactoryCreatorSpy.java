package se.uu.ub.cora.clientdata.spies;

import se.uu.ub.cora.clientdata.converter.ClientDataToJsonConverterFactory;
import se.uu.ub.cora.clientdata.converter.ClientDataToJsonConverterFactoryCreator;
import se.uu.ub.cora.testutils.mcr.MethodCallRecorder;
import se.uu.ub.cora.testutils.mrv.MethodReturnValues;

public class ClientDataToJsonConverterFactoryCreatorSpy
		implements ClientDataToJsonConverterFactoryCreator {

	public MethodCallRecorder MCR = new MethodCallRecorder();
	public MethodReturnValues MRV = new MethodReturnValues();

	public ClientDataToJsonConverterFactoryCreatorSpy() {
		MCR.useMRV(MRV);
		MRV.setDefaultReturnValuesSupplier("createFactory",
				ClientDataToJsonConverterFactorySpy::new);
	}

	@Override
	public ClientDataToJsonConverterFactory createFactory() {
		return (ClientDataToJsonConverterFactory) MCR.addCallAndReturnFromMRV();
	}

}
