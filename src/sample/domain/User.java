package sample.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
@Entity
@Table(name="USER")
public class User implements UserDetails {
	private static final long serialVersionUID = 9179584208028190285L;
	
	@Id
	@Column(name="no")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long no;
	
	@Column(name="id")
	private String username;
	
	@Column(name="pw")
    private String password;
	
	@Column(name="enabled")
    private boolean enabled;
    
    @OneToMany(fetch=FetchType.EAGER, orphanRemoval=true, cascade=CascadeType.ALL)
    @JoinColumn(name="user_no")
    private Set<UserAuth> authorities;
    
    public User() { }
    
    public User(long no, String userName, String password, Set<UserAuth> authorities, boolean enabled) {
    	super();
    	this.no = no;
        this.username = userName;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }
    
    public long getNo() {
		return no;
	}
    
    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    public void addAuthorities(UserAuth userAuth) {
    	 if(authorities == null) {
    		 authorities = new HashSet<>();
    	 }
    	 authorities.add(userAuth);
    }
    
    public void setNo(long no) {
		this.no = no;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(Set<UserAuth> authorities) {
		this.authorities = authorities;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
  
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
  
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
  
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    
 }
