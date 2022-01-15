package yuan.study.demo.service.designPattern.bridge;

public class FoldedPhone extends Phone{

	public FoldedPhone(Brand brand) {
		super(brand);
	}
	public void open() {
		super.open();
		System.out.println("打开折叠样式手机");
	}
	
	public void close() {
		super.close();
		System.out.println("关闭折叠样式手机");
	}
}