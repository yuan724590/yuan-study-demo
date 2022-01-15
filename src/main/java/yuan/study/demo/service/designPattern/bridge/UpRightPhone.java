package yuan.study.demo.service.designPattern.bridge;

public class UpRightPhone extends Phone{


	public UpRightPhone(Brand brand) {
		super(brand);
	}

	public void open() {
		super.open();
		System.out.println("打开直立样式手机");
	}
	
	public void close() {
		super.close();
		System.out.println("关闭直立样式手机");
	}
}