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

	public CheckServerStatus(String ipAddress, List<Credential> credentials) {
		this.credentials = credentials;
		this.ipAddress = ipAddress;
	}

	@Override
	public ExecutionResult call() throws Exception {

		Session session = createSession(ipAddress, credentials);
		ExecutionResult executionResult = null;
		JSch jSch = new JSch();
		session = getTrySession(ipAddress, credentials.get(0), session, jSch);
		if (session==null) {
			session = getTrySession(ipAddress, credentials.get(1), session, jSch);
			
		}
		
		if(error=="" || error==null) {
			executionResult=executeCommands(session);
			
		}
		
		return executionResult;
	}

	private ExecutionResult executeCommands(Session session) {
		boolean connected = session.isConnected();
		if (connected) {
			
			String arg0 = null;
			try {
				session.openChannel(arg0);
				
				
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	private Session createSession(String ipAddress2, List<Credential> credentials) {

		Session session = null;

		JSch jSch = new JSch();
		session = getTrySession(ipAddress2, credentials.get(0), session, jSch);
		if (session==null) {
			session = getTrySession(ipAddress2, credentials.get(1), session, jSch);
			
		}
		return session;
	}

	private Session getTrySession(String ipAddress2, Credential credentials, Session session, JSch jSch) {
		try {
			session = jSch.getSession(credentials.getUserName(), ipAddress2);
			session.setPassword(credentials.getPassword());
			session.connect();
			
		} catch (JSchException e) {
			error="Error Msg:"+e.getMessage();
			e.printStackTrace();
		}
		return session;
	}

}
