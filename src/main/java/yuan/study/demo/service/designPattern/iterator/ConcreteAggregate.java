package yuan.study.demo.service.designPattern.iterator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConcreteAggregate extends Aggregate {
    
    private Object[] objArray;

    @Override
    public Iterator createIterator() {
        
        return new ConcreteIterator(this);
    }

    /**
     * 取值方法：向外界提供聚集元素
     */
    Object getElement(int index){
        
        if(index < objArray.length){
            return objArray[index];
        }else{
            return null;
        }
    }

    /**
     * 取值方法：向外界提供聚集的大小
     */
    int size(){

        return objArray.length;
    }
}