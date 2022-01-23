package yuan.study.demo.service.designPattern.observer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WeatherData extends Subject{

    /**
     * 温度
     */
	private Float temperature;

    /**
     * 压力
     */
	private Float pressure;

    /**
     * 湿度
     */
	private Float humidity;

    /**
     * 观察者列表
     */
	private List<Observer> observerList;

	public WeatherData() { 

		this.observerList = new ArrayList<>();
	}

    /**
     * 当数据有更新时，就调用 setData
     */
	public void setData(Float temperature, Float pressure, Float humidity) {
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		//通知观察者
        notifyObserverList();
	}

	@Override
	public void registerObserver(Observer observer) {

		observerList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {

		observerList.remove(observer);
	}

	@Override
	public void notifyObserverList() {

		observerList.forEach(item -> item.update(temperature, pressure, humidity));
	}
}