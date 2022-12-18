package yuan.study.demo.entity;


public class BitMap {

    private int[] temp;

    private int size;

    public BitMap(int size) {
        this.size = size;
        //初始化temp
        //由于一个int占32位，可表示32个数字，那么如果想要存储size个数字，则需要
        this.temp = new int[size >> 5 + 1];
    }

    /**
     * 存储num
     *
     * @param num 需要存储的整数
     */
    public void setBit(int num) {
        if (num < 0 || num > size) {
            throw new RuntimeException("");
        }
        int index = num >> 5;
        int bitIndex = num % 32;
        //按位存储, 即index为第几个32位组合, bitIndex为该组合内第几位数, 然后将其值相加(保留原有结果)
        temp[index] |= (1 << bitIndex);
    }

    /**
     * 查找num
     *
     * @param num 需要存储的整数
     */
    public boolean getBit(int num) {
        if (num < 0 || num > size) {
            throw new RuntimeException("");
        }
        int index = num >> 5;
        int bitIndex = num % 32;

        return (temp[index] & (1 << bitIndex)) != 0;
    }

    /**
     * 删除num
     *
     * @param num
     */
    public void deleteBit(int num) {
        if (num < 0 || num > size) {
            throw new RuntimeException("");
        }
        int index = num >> 5;
        int bitIndex = num % 32;

        temp[index] &= (~(1 << bitIndex));
    }
}