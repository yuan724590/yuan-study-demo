package yuan.study.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfoCopy {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 商品品牌名
     */
    private String goodsBrand;

    /**
     * 商品类型 1-CPU 2-主板 3-显卡 4-显示器 5-内存条 6-硬盘
     */
    private Byte goodsType;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品史低价格
     */
    private BigDecimal historicalLowestPrice;

    /**
     * 商品图片url
     */
    private String imageUrl;

    /**
     * 商品图片的高度
     */
    private Integer imageHeight;

    /**
     * 商品图片的宽度
     */
    private Integer imageWidth;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;
}
