package com.chen.service.imp.area;

import com.chen.dao.area.AreaRepository;
import com.chen.model.area.Area;
import com.chen.results.AmapResult;
import com.chen.service.area.AreaService;
import com.chen.service.imp.BaseServiceImp;
import com.chen.service.third.AmapService;
import com.chen.vos.AreaVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImp extends BaseServiceImp<Area, AreaVO> implements AreaService, InitializingBean {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private void setBaseRepository() {
        setBaseRepository(this.areaRepository);
    }

    @Autowired
    private AmapService amapService;

    @Override
    public Area voToModel(AreaVO vo) {
        Area area = new Area();
        area.setCode(vo.getCode());
        area.setName(vo.getName());
        area.setCenter(vo.getCenter());
        area.setLevel(vo.getLevel());
        area.setParent(areaRepository.findByID(vo.getParentId()));
        return area;
    }

    @Override
    public void afterPropertiesSet() {
        if (areaRepository.fetchAll().isEmpty()) {
            AmapResult result = amapService.districtList();
            for (AmapResult.District district : result.getDistricts()) {
                AreaVO areaVO = new AreaVO();
                areaVO.setCode(district.getAdcode());
                areaVO.setName(district.getName());
                areaVO.setLevel(district.getLevel());
                areaVO.setCenter(district.getCenter());
                Area area = add(areaVO);
                init(district.getDistricts(), area.getId());
            }
        }
    }

    private void init(List<AmapResult.District> districts, Long parentId) {
        for (AmapResult.District district : districts) {
            AreaVO areaVO = new AreaVO();
            areaVO.setCode(district.getAdcode());
            areaVO.setName(district.getName());
            areaVO.setLevel(district.getLevel());
            areaVO.setCenter(district.getCenter());
            areaVO.setParentId(parentId);
            Area area = add(areaVO);
            init(district.getDistricts(), area.getId());
        }
    }

}
