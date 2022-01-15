package yuan.study.demo.service.designPattern.observer;

public interface ObserverInterface {

	/**
	 * 更新天气信息
	 */
	void update(float temperature, float pressure, float humidity);

	/**
	 * 展示天气信息
	 */
	void display();
}