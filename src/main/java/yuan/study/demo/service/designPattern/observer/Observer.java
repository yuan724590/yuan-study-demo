package yuan.study.demo.service.designPattern.observer;

public abstract class Observer {

	/**
	 * 更新天气信息
	 */
	public abstract void update(float temperature, float pressure, float humidity);

	/**
	 * 展示天气信息
	 */
	public abstract void display();
}