package yuan.study.demo.service.designPattern.bridge;

public class Vivo implements Brand{

	@Override
	public void open() {
		System.out.println("Vivo手机开机");
	}

	@Override
	public void close() {
		System.out.println("Vivo手机关机");
	}
}