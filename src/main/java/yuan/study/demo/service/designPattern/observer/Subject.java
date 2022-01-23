package yuan.study.demo.service.designPattern.observer;

public abstract class Subject {

	/**
	 * 注册一个观察者
	 */
	public abstract void registerObserver(Observer observer);

	/**
	 * 移除一个观察者
	 */
	public abstract void removeObserver(Observer observer);

	/**
	 * 遍历所有的观察者，并通知
	 */
	public abstract void notifyObserverList();

}