package yuan.study.demo.service.designPattern.observer;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WeatherData implements Subject{

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
	private List<ObserverInterface> observerInterfaceList;

	public WeatherData() { 
		this.observerInterfaceList = new ArrayList<>();
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
	public void registerObserver(ObserverInterface observerInterface) {

		observerInterfaceList.add(observerInterface);
	}

	@Override
	public void removeObserver(ObserverInterface observerInterface) {

		observerInterfaceList.remove(observerInterface);
	}

	@Override
	public void notifyObserverList() {

		observerInterfaceList.forEach(item -> item.update(temperature, pressure, humidity));
	}
}