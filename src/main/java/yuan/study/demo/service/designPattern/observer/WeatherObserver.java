package yuan.study.demo.service.designPattern.observer;

public class WeatherObserver extends Observer {

	/**
	 * 温度
	 */
	private Float temperature;

	/**
	 * 气压
	 */
	private Float pressure;

	/**
	 * 湿度
	 */
	private Float humidity;

	@Override
	public void update(float temperature, float pressure, float humidity) {
		this.temperature = temperature; 
		this.pressure = pressure; 
		this.humidity = humidity;
		display();
	}

    @Override
	public void display() {
		System.out.println("今天温度是: " + temperature);
		System.out.println("今天压力是: " + pressure);
		System.out.println("今天湿度是: " + humidity);
	}
}