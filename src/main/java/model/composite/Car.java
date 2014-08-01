package model.composite;

import composite.id.CarPK;

/**
 * 利用xml配置联合主键
 * 
 * @author Administrator
 * 
 */
public class Car {

	private CarPK carPK;
	private Long width;
	private Long height;
	private String engine;

	public CarPK getCarPK() {
		return carPK;
	}

	public void setCarPK(CarPK carPK) {
		this.carPK = carPK;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

}
