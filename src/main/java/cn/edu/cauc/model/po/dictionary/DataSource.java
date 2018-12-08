package cn.edu.cauc.model.po.dictionary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="dic_data_source")
public class DataSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8144765470034217726L;
	
	@GenericGenerator(name = "generator", strategy = "increment")
 	@Id
 	@GeneratedValue(generator = "generator")
 	@Column(name = "ID", unique = true, nullable = false)
	private Integer id; //主键ID
	
	@Column(name="DIC_KEY", length=2)
	private String key; //字典key
	
	@Column(name="DIC_VALUE", length=256)
	private String value; //值
	
	@Column(name="IS_VALID")
	private boolean isValid = true; //是否有效

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
