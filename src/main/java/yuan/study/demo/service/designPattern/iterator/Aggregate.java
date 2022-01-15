package yuan.study.demo.service.designPattern.iterator;

public abstract class Aggregate {

    /**
     * 创建相应迭代子对象的接口
     */
    public abstract Iterator createIterator();
}