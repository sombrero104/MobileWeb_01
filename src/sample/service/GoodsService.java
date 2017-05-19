package sample.service;

import java.util.List;

import sample.domain.Goods;

public interface GoodsService {
	
	public List<Goods> getGoodsList(int startNum, int endNum);
	
}
