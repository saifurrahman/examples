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
		List<String> list = new ArrayList<>();
		list.add("a");
		
		List<String> list2 = new ArrayList<>();
		list2.add("b");
		
		ipAddressMap.put("a", list);
		ipAddressMap.put("b", list2);
	//	 ipAddressMap.put("c", list);

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
				ExecutionResult executionResult = future.get();
				String ipAddress = executionResult.getIpAddress();
				
				List<String> originalExcelRow = ipAddressMap.get(ipAddress);
				System.out.println("Result-->"+ipAddress+"--"+executionResult.getError()+"--"+originalExcelRow);
				originalExcelRow.add(executionResult.getError());
				
				ipAddressMap.put(ipAddress, originalExcelRow);

			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();

		System.out.println("Result  " + ipAddressMap);

	}

}
