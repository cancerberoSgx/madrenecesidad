package org.sgx.madrenecesidad.client.ui.action;


/**
 * 
 * @author sg
 *
 */
public class ActionManager {
	private static ActionManager instance;

	private ActionManager() {
	}

	public static ActionManager getInstance() {
		if (instance == null)
			instance = new ActionManager();
		return instance;
	}

	AppStateAction currentAction = null;

	public void performAction(AppStateAction a, Object actionConfig) {
		if (currentAction != null)
			currentAction.uninstall();
		currentAction = a;
		a.perform(actionConfig);
	}
}
