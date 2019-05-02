package eu.su.mas.dedaleEtu.mas.behaviours.explore;

import eu.su.mas.dedaleEtu.mas.agents.ExploreMultiAgent;
import eu.su.mas.dedaleEtu.mas.behaviours.FSMCodes;
import jade.core.behaviours.OneShotBehaviour;

public class StartExploreBehaviour extends OneShotBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4094788259802184113L;
	
	ExploreMultiAgent _myAgent;
	
	public StartExploreBehaviour(ExploreMultiAgent myagent)
	{
		super(myagent);
		this._myAgent = myagent;
		this._myAgent.setPreviousNode(this._myAgent.getCurrentPosition());
	}
	
	@Override
	public void action() 
	{
		this._myAgent.registerService("EXPLORE");
	}

	public int onEnd()
	{
		return FSMCodes.Events.SUCESS.ordinal();
	}
}
