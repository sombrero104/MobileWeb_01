package sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="USER_AUTH")
public class UserAuth implements GrantedAuthority {
	private static final long serialVersionUID = 2441184052524029737L;
	
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long no;
	
	@Column(name="authority")
	private String authority;
	
	@Column(name="user_no")
	private long user_no;
	
	public UserAuth() { }
	
	public UserAuth(String authority) {
		super();
		this.authority = authority;
	}
	
	public long getNo() {
		return no;
	}
	
	@Override
	public String getAuthority() {
		return this.authority;
	}
	
	public long getUser_no() {
		return user_no;
	}
	
	public void setNo(long no) {
		this.no = no;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	
}
