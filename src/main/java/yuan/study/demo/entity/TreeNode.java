package yuan.study.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
}