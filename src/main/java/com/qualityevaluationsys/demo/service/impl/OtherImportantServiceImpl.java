package com.qualityevaluationsys.demo.service.impl;

import com.qiniu.util.StringUtils;
import com.qualityevaluationsys.demo.dao.OtherImportantMapper;
import com.qualityevaluationsys.demo.domain.ClassExample;
import com.qualityevaluationsys.demo.domain.OtherImportant;
import com.qualityevaluationsys.demo.domain.OtherImportantExample;
import com.qualityevaluationsys.demo.service.OtherImportantService;
import com.qualityevaluationsys.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherImportantServiceImpl implements OtherImportantService {
    @Autowired
    private OtherImportantMapper otherImportantMapper;

    @Override
    public int deleteByPrimaryKey(Integer oid) {
        return otherImportantMapper.deleteByPrimaryKey(oid);
    }

    @Override
    public int insertSelective(OtherImportant record) {
        return otherImportantMapper.insertSelective(record);
    }

    @Override
    public OtherImportant selectByPrimaryKey(Integer oid) {
        return otherImportantMapper.selectByPrimaryKey(oid);
    }

    @Override
    public int updateByPrimaryKeySelective(OtherImportant record) {
        return otherImportantMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageBean getPageBean(Integer limit, String sort, Integer page, OtherImportant otherImportant) {
        OtherImportantExample example=new OtherImportantExample();
        if(otherImportant!=null){
            OtherImportantExample.Criteria criteria = example.createCriteria();
            if(!StringUtils.isNullOrEmpty(otherImportant.getSname())){
                criteria.andSnameLike("%"+otherImportant.getSname()+"%");
            }
            if(!StringUtils.isNullOrEmpty(otherImportant.getSclass())){
                criteria.andSclassLike("%"+otherImportant.getSclass()+"%");
            }
        }
        if(!StringUtils.isNullOrEmpty(sort)&& sort.equals("-id")){
            example.setOrderByClause("cid desc");
        }else if(!StringUtils.isNullOrEmpty(sort)){
            example.setOrderByClause("cid asc");
        }

        int count = (int) otherImportantMapper.countByExample(example);
        PageBean pageBean=new PageBean(page,count,limit);
        example.setLimit(limit);
        example.setOffset(pageBean.getStart());
        pageBean.setList(otherImportantMapper.selectByExample(example));
        return pageBean;
    }
}
