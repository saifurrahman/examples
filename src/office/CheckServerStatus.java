package office;

import java.util.List;
import java.util.concurrent.Callable;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class CheckServerStatus implements Callable<ExecutionResult> {

	String ipAddress;
	List<Credential> credentials;
	String error;
	private boolean ifAuthFailed;

	public CheckServerStatus(String ipAddress, List<Credential> credentials) {
		this.credentials = credentials;
		this.ipAddress = ipAddress;
	}

	@Override
	public ExecutionResult call() throws Exception {
		// System.out.println("Execution Thread for ip-->" + ipAddress);
		Session session = createSession(ipAddress, credentials);
		ExecutionResult executionResult = null;
		if (session != null) {
			executionResult = executeCommands(session);

		} else {
			executionResult = new ExecutionResult(ipAddress, error);

		}

		return executionResult;
	}

	private ExecutionResult executeCommands(Session session) {
		boolean connected = session.isConnected();
		ExecutionResult result = new ExecutionResult(ipAddress, error);

		if (connected) {

			String arg0 = null;
			try {
				session.openChannel(arg0);

				// command execute

			} catch (JSchException e) {
				error = "Error Msg:" + e.getMessage();

			} finally {
				result = new ExecutionResult(ipAddress, error);
			}
		}else {
			error = "Session not able to connect";
			result = new ExecutionResult(ipAddress, error);
		}
		return result;
	}

	private Session createSession(String ipAddress2, List<Credential> credentials) {

		Session session = null;

		JSch jSch = new JSch();
		// System.out.println(credentials.get(0).getUserName());
		// System.out.println(credentials.get(1).getUserName());
		session = getTrySession(ipAddress2, credentials.get(0), session, jSch);
		if (ifAuthFailed) {
			session = getTrySession(ipAddress2, credentials.get(1), session, jSch);

		}else {
			System.out.println("Retruning session-->"+session.isConnected());
		}
		return session;
	}

	private Session getTrySession(String ipAddress2, Credential credentials, Session session, JSch jSch) {

		System.out.println("trying session for -->" + ipAddress2 + ":" + credentials.getUserName());
		try {
			session = jSch.getSession(credentials.getUserName(), ipAddress2);
			session.setPassword(credentials.getPassword());
			// session.connect();

			Thread.sleep(3000);

		} catch (JSchException e) {
			ifAuthFailed=true;
			error = "Error Msg:" + e.getMessage();
			// e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ifAuthFailed=true;
		return session;
	}

}
