package yuan.study.demo.service.designPattern.observer;

public interface Subject {

	/**
	 * 注册一个观察者
	 */
	void registerObserver(ObserverInterface observerInterface);

	/**
	 * 移除一个观察者
	 */
	void removeObserver(ObserverInterface observerInterface);

	/**
	 * 遍历所有的观察者，并通知
	 */
	void notifyObserverList();

}