package yuan.study.demo.service.designPattern.iterator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConcreteIterator implements Iterator {

    /**
     * 持有被迭代的具体的聚合对象
     */
    private ConcreteAggregate agg;

    /**
     * 内部索引，记录当前迭代到的索引位置
     */
    private int index;

    /**
     * 记录当前聚集对象的大小
     */
    private int size;
    
    ConcreteIterator(ConcreteAggregate agg){
        this.agg = agg;
        this.size = agg.size();
        index = 0;
    }


    @Override
    public Object currentItem() {

        return agg.getElement(index);
    }

    @Override
    public void first() {
        
        index = 0;
    }

    @Override
    public boolean isDone() {
        return index >= size;
    }

    @Override
    public void next() {
        if(index < size) {
            index ++;
        }
    }
}