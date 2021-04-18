package yuan.study.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yuan
 * @Date 2021/4/18 11:03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students implements Cloneable, Serializable {

    /**
     * 学号
     */
    private int id;

    /**
     * 名字
     */
    private String name;

    private Students(int id){
        this.id = id;
    }

    Students(String name){
        this.name = name;
    }

    @Override
    public Students clone() {
        Students students = null;
        try{
            students = (Students)super.clone();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
