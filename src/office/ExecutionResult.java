package office;

public class ExecutionResult {
	String ipAddress;
	String cupStatus;
	String cupLog;

	String memoryStatus;
	String memoryLog;
	private String error;

	
	
	public ExecutionResult(String ipAddress, String error) {
		
		this.ipAddress = ipAddress;
		this.error = error;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCupStatus() {
		return cupStatus;
	}

	public void setCupStatus(String cupStatus) {
		this.cupStatus = cupStatus;
	}

	public String getCupLog() {
		return cupLog;
	}

	public void setCupLog(String cupLog) {
		this.cupLog = cupLog;
	}

	public String getMemoryStatus() {
		return memoryStatus;
	}

	public void setMemoryStatus(String memoryStatus) {
		this.memoryStatus = memoryStatus;
	}

	public String getMemoryLog() {
		return memoryLog;
	}

	public void setMemoryLog(String memoryLog) {
		this.memoryLog = memoryLog;
	}

	public void setError(String string) {
		this.error=string;		
	}

	public String getError() {
		return error;
	}

}
