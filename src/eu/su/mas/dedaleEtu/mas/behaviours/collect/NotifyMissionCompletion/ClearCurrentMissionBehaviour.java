package eu.su.mas.dedaleEtu.mas.behaviours.collect.NotifyMissionCompletion;

import eu.su.mas.dedaleEtu.mas.agents.CollectMultiAgent;
import eu.su.mas.dedaleEtu.mas.behaviours.FSMCodes;
import jade.core.behaviours.OneShotBehaviour;

public class ClearCurrentMissionBehaviour extends OneShotBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -291661419440347134L;
	private CollectMultiAgent _myAgent;
	
	public ClearCurrentMissionBehaviour(CollectMultiAgent myagent)
	{
		super(myagent);
		this._myAgent = myagent;
	}

	@Override
	public void action() 
	{
		this._myAgent.setCurrentMission(null);
	}
	
	public int onEnd()
	{
		return FSMCodes.Events.SUCESS.ordinal();
	}

}
