package yuan.study.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuan
 * @Date 2021/4/18 11:03
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    /**
     * 书号
     */
    private int id;

    /**
     * 名字
     */
    private String name;
}
