package com.shop.domains.users;

import com.shop.domains.userRoles.UserRolesEntity;
import com.shop.domains.userItems.UserItemEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phone;
    private Date created;
    private Date updated;
    @Column(name = "enabled")
    private Boolean isEnabled;
    @Column(name = "credentials_not_expired")
    private Boolean isCredentialsNotExpired;
    @Column(name = "not_locked")
    private Boolean isAccountNonLocked;
    @Column(name = "not_expired")
    private Boolean isAccountNonExpired;

    @OneToMany(mappedBy = "user")
    private Set<UserItemEntity> items;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRolesEntity> roles;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getCredentialsNotExpired() {
        return isCredentialsNotExpired;
    }

    public void setCredentialsNotExpired(Boolean credentialsNotExpired) {
        isCredentialsNotExpired = credentialsNotExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public Set<UserItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<UserItemEntity> items) {
        this.items = items;
    }

    public Set<UserRolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRolesEntity> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated) &&
                Objects.equals(isEnabled, that.isEnabled) &&
                Objects.equals(isCredentialsNotExpired, that.isCredentialsNotExpired) &&
                Objects.equals(isAccountNonLocked, that.isAccountNonLocked) &&
                Objects.equals(isAccountNonExpired, that.isAccountNonExpired)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, phone, created, updated, isEnabled, isCredentialsNotExpired, isAccountNonLocked, isAccountNonExpired);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", isEnabled=" + isEnabled +
                ", isCredentialsNotExpired=" + isCredentialsNotExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isAccountNonExpired=" + isAccountNonExpired +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
