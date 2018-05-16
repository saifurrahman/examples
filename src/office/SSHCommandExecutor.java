package office;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class SSHCommandExecutor {

	public static void main(String[] args) {

		List<Credential> credentials = new ArrayList<>();
		credentials.add(new Credential("abc", "abc"));
		credentials.add(new Credential("xyz", "xyz"));

		String filePath = "";

		Map<String, Integer> columnMapping = new Hashtable<>();

		Map<String, List<String>> ipAddressMap = new HashMap<>();

		// String ipAddress = null;
		// Records result = checkServerStatus(ipAddress, credentials);
		final ExecutorService service = Executors.newCachedThreadPool();
		final List<FutureTask<ExecutionResult>> taskList = new ArrayList<FutureTask<ExecutionResult>>();

		FutureTask<ExecutionResult> futureTask;
		for (final String ipAddress : ipAddressMap.keySet()) {
			futureTask = new FutureTask<>(new CheckServerStatus(ipAddress, credentials));
			taskList.add(futureTask);
			service.execute(futureTask);
		}

		for (final FutureTask<ExecutionResult> future : taskList) {
			try {
				ExecutionResult processResult = future.get();
				List<String> list2 = ipAddressMap.get(processResult.getIpAddress());
				// list2.add(processResult.getResult());
				ipAddressMap.put(processResult.getIpAddress(), list2);

			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();

	}

}
