package eu.su.mas.dedaleEtu.mas.behaviours.collect.DoMission;

import eu.su.mas.dedaleEtu.mas.agents.CollectMultiAgent;
import eu.su.mas.dedaleEtu.mas.behaviours.FSMCodes;
import eu.su.mas.dedaleEtu.mas.behaviours.collect.GetMission.CheckTimeOutBehaviour;
import jade.core.behaviours.FSMBehaviour;

public class OpenChestFSMBehaviour extends FSMBehaviour 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7050559593740836987L;
	private CollectMultiAgent _myAgent;

	public OpenChestFSMBehaviour(CollectMultiAgent myagent)
	{
		super(myagent);
		this._myAgent = myagent;
		
		this.registerFirstState(new StartOpenChestBehaviour(myagent), "START-OPEN-CHEST");
		this.registerState(new RequestSupportBehaviour(myagent), "REQUEST-SUPPORT");
		this.registerState(new OpenChestBehaviour(myagent), "OPEN-CHEST");
		this.registerState(new CheckTimeOutBehaviour(myagent, 500), "CHECK-TIMEOUT");
		this.registerState(new DisbandSupportBehaviour(myagent), "DISBAND-SUPPORT");
		this.registerLastState(new EndOpenChestBehaviour(myagent), "END-OPEN-CHEST");
		
		this.registerTransition("START-OPEN-CHEST", "CHECK-TIMEOUT", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("CHECK-TIMEOUT", "OPEN-CHEST", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("CHECK-TIMEOUT",  "DISBAND-SUPPORT", FSMCodes.Events.FAILURE.ordinal());
		this.registerTransition("OPEN-CHEST", "DISBAND-SUPPORT", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("OPEN-CHEST", "REQUEST-SUPPORT", FSMCodes.Events.FAILURE.ordinal());
		this.registerTransition("REQUEST-SUPPORT", "CHECK-TIMEOUT", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("DISBAND-SUPPORT", "END-OPEN-CHEST", FSMCodes.Events.SUCESS.ordinal());
	}
	
	public int onEnd()
	{
		this.resetChildren();
		this.reset();
		return FSMCodes.Events.SUCESS.ordinal();
	}
	
}
