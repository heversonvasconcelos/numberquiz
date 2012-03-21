package sampleapp.numberquiz.ui.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import sampleapp.numberquiz.ui.util.Constants;
import sampleapp.numberquiz.ui.util.SessionUtil;

/**
 * 
 * @author heverson.vasconcelos
 */
public class LoggedInCheck implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		boolean isLoginPage = (currentPage.lastIndexOf("index.xhtml") > -1);
		boolean isUserNotLogged = (currentPage
				.lastIndexOf("usernotlogged.xhtml") > -1);
		Object currentUser = SessionUtil.getAttribute(Constants.LOGGED_USER);

		if (!isLoginPage && !isUserNotLogged && currentUser == null) {
			NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(facesContext, null, "usernotlogged");
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
