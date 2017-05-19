package sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.dao.GoodsDao;
import sample.domain.Goods;
import sample.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	GoodsDao goodsDao;
	
	public List<Goods> getGoodsList(int startNum, int endNum) {
		return goodsDao.getGoodsList(startNum, endNum);
	}
	
}
