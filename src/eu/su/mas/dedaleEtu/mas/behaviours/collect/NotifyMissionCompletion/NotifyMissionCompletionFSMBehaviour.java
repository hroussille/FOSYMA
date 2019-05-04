package eu.su.mas.dedaleEtu.mas.behaviours.collect.NotifyMissionCompletion;

import eu.su.mas.dedaleEtu.mas.agents.CollectMultiAgent;
import eu.su.mas.dedaleEtu.mas.behaviours.FSMCodes;
import eu.su.mas.dedaleEtu.mas.behaviours.collect.GetMission.CheckTimeOutBehaviour;
import jade.core.behaviours.FSMBehaviour;

public class NotifyMissionCompletionFSMBehaviour extends FSMBehaviour {

	private static final long serialVersionUID = 4274135992335616378L;
	private CollectMultiAgent _myAgent;
	
	public NotifyMissionCompletionFSMBehaviour(CollectMultiAgent myagent) {
		super(myagent);
		this._myAgent = myagent;
		
		this.registerFirstState(new StartNotifyMissionCompletionBehaviour(this._myAgent), "START-NOTIFY-COMPLETION");
		this.registerState(new SendMissionCompletionNotificationBehaviour(this._myAgent), "SEND-NOTIFY-COMPLETION");
		this.registerState(new CheckTimeOutBehaviour(this._myAgent, 20), "CHECK-TIMEOUT");
		this.registerState(new ReceiveMissionCompletionNotificationACKBehaviour(this._myAgent), "RECEIVE-NOTIFY-COMPLETION-ACK");
		this.registerState(new SendUpdateTankerKnowledgeBehaviour(this._myAgent), "SEND-UPDATE-TANKER-KNOWLEDGE");
		this.registerState(new ClearCurrentMissionBehaviour(this._myAgent), "CLEAR-CURRENT-MISSION");
		this.registerLastState(new EndNotifyMissionCompletionBehaviour(this._myAgent), "END-NOTIFY-COMPLETION");
		
		this.registerTransition("START-NOTIFY-COMPLETION", "SEND-UPDATE-TANKER-KNOWLEDGE", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("SEND-UPDATE-TANKER-KNOWLEDGE", "SEND-NOTIFY-COMPLETION", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("SEND-NOTIFY-COMPLETION", "CHECK-TIMEOUT", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("CHECK-TIMEOUT", "RECEIVE-NOTIFY-COMPLETION-ACK", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("CHECK-TIMEOUT", "END-NOTIFY-COMPLETION", FSMCodes.Events.FAILURE.ordinal());
		this.registerTransition("RECEIVE-NOTIFY-COMPLETION-ACK", "CHECK-TIMEOUT", FSMCodes.Events.FAILURE.ordinal());
		this.registerTransition("RECEIVE-NOTIFY-COMPLETION-ACK", "CLEAR-CURRENT-MISSION", FSMCodes.Events.SUCESS.ordinal());
		this.registerTransition("CLEAR-CURRENT-MISSION", "END-NOTIFY-COMPLETION", FSMCodes.Events.SUCESS.ordinal());
	}

	public int onEnd() {
		this.resetChildren();
		this.reset();
		
		if (this._myAgent.getCurrentMission() == null)
			return FSMCodes.Events.SUCESS.ordinal();
		return FSMCodes.Events.FAILURE.ordinal();
	}
}
