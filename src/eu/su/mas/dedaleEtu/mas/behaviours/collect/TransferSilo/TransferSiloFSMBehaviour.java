package eu.su.mas.dedaleEtu.mas.behaviours.collect.TransferSilo;

import eu.su.mas.dedaleEtu.mas.agents.CollectMultiAgent;
import eu.su.mas.dedaleEtu.mas.behaviours.FSMCodes;
import jade.core.behaviours.FSMBehaviour;

public class TransferSiloFSMBehaviour extends FSMBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2439989609188495215L;
	private CollectMultiAgent _myAgent;

	public TransferSiloFSMBehaviour(CollectMultiAgent myagent) {
		super(myagent);
		this._myAgent = myagent;
		
		this.registerFirstState(new StartTransferSiloBehaviour(myagent), "START-TRANSFER-SILO");
		this.registerState(new TransferSiloBehaviour(myagent), "TRANSFER-SILO");
		this.registerLastState(new EndTransferSiloBehaviour(myagent), "END-TRANSFER-SILO");
		
		this.registerTransition("START-TRANSFER-SILO", "TRANSFER-SILO", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("TRANSFER-SILO", "END-TRANSFER-SILO", FSMCodes.Events.SUCESS.ordinal());	
	}
	
	public int onEnd() {
		this.resetChildren();
		this.reset();
		
		if (this._myAgent.getBackPackFreeSpace() > 0)
			return FSMCodes.Events.SUCESS.ordinal();
		return FSMCodes.Events.FAILURE.ordinal();
	}
}
