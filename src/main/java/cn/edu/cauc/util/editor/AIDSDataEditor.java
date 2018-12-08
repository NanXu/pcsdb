package cn.edu.cauc.util.editor;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.edu.cauc.model.po.data.AidsRawData;
import au.com.bytecode.opencsv.CSVReader;

/**
 * 作者： 徐楠
 *
 * 描述：<p>AIDS 数据解析绑定</p>
 * 创建时间：2016年9月19日
 */
public class AIDSDataEditor extends PropertyEditorSupport {

	public void setValue(Object value) {  
        if (value instanceof MultipartFile) {//处理上传文件，此处默认上传的是格式正确的CSV文件  
            MultipartFile multipartFile = (MultipartFile) value;  
            System.out.println(multipartFile.getContentType());//打印Content-Type  
            try {  
                //使用第三方开源类库OpenCSV来读取CSV文件  
                CSVReader reader = new CSVReader(new InputStreamReader(  
                        multipartFile.getInputStream()));  
                String[] nextLine;  
                // 去除第一行header信息  
                reader.readNext();  
  
                List<AidsRawData> rawDataList = new ArrayList<AidsRawData>();  
                while ((nextLine = reader.readNext()) != null) {  
//                	AIDSRawData rawData = new AIDSRawData();
//                    email.setName(nextLine[2]);  
//                    email.setEmail(nextLine[4]);  
//                    emails.add(email);  
                }  
                  
                //绑定数据列表  
                super.setValue(rawDataList);  
            } catch (IOException ex) {  
                throw new IllegalArgumentException(  
                        "Cannot read contents of multipart file: "  
                                + ex.getMessage());  
            }  
        } else if (value instanceof byte[]) {  
            super.setValue(value);  
        } else {  
            super.setValue(value != null ? value.toString().getBytes() : null);  
        }  
    }  
}
