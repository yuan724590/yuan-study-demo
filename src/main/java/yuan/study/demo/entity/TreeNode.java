package yuan.study.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class TreeNode {

    /**
     * 节点的值
     */
    public Integer value;

    /**
     * 左节点
     */
    public TreeNode leftNode;

    /**
     * 右节点
     */
    public TreeNode rightNode;

    public TreeNode(Integer value){
        this.value = value;
    }
}