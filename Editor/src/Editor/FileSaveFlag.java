package Editor;

public class FileSaveFlag {
	
	private boolean flag;
	
	FileSaveFlag(){
		flag = true;
	}
	
	FileSaveFlag(boolean flag){
		this.setFlag(flag);
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
}
