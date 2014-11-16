package com.hitpoint.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hitpoint.surveypark.dao.BaseDao;
import com.hitpoint.surveypark.model.security.Right;
import com.hitpoint.surveypark.service.RightService;
import com.hitpoint.surveypark.util.ValidateUtil;

@Service("rightService")
public class RightSerivceImpl extends BaseServiceImpl<Right> implements RightService {
	
	@Resource(name="rightDao")
	public void setDao(BaseDao<Right> dao){
		super.setDao(dao);
	}

	public void saveOrUpdateRight(Right r) {
		int pos = 0;
		long code =1L;
		if(r.getId() == null){
			String hql = "from Right r order by r.rightPos desc,r.rightCode desc";
			List<Right> rights = this.findEntityByHQL(hql);
			if(!ValidateUtil.isValid(rights)){
				pos = 0;
				code = 1L;
			}
			else{
				//得到最上面的right
				Right top = rights.get(0);
				int topPos = top.getRightPos();
				long topCode = top.getRightCode();
				//判断权限码是否达到最大值
				if(topCode >= (1l << 60)){
					pos = (int) (topCode + 1);
					code = 1;
				}
				else{
					pos = topPos;
					code = topCode << 1;
				}
			}
			r.setRightPos(pos);
			r.setRightCode(code);
		}
		this.saveOrUpdateRight(r);
	}
}
