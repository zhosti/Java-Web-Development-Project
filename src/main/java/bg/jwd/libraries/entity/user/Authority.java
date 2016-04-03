package bg.jwd.libraries.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY_TYPES")
public class Authority {
	
	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "AUTHORITY")
	private String authority;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
