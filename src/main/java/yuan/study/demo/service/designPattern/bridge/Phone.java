package yuan.study.demo.service.designPattern.bridge;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Phone {

	/**
	 * 组合品牌
	 */
	private Brand brand;
	
	public void open() {

		brand.open();
	}

	public void close() {

		brand.close();
	}
}