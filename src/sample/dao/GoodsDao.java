package sample.dao;

import java.util.List;

import sample.domain.Goods;

public interface GoodsDao {
	
	public List<Goods> getGoodsList(int startNum, int endNum);
	
}
