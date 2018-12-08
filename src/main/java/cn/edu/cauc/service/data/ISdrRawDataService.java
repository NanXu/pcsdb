package cn.edu.cauc.service.data;

import cn.edu.cauc.model.po.data.SdrRawData;
import cn.edu.cauc.model.vo.Page;

public interface ISdrRawDataService {

	public Page<SdrRawData> findRawList(SdrRawData sdrRawData, int startPage,
			Integer pageSize);

	public void addSdrTxtData();

}
