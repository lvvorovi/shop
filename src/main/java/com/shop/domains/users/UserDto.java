package com.shop.domains.users;

import com.shop.domains.userRoles.UserRolesEntity;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class UserDto extends RepresentationModel<UserDto> {

    private Long id;
    @NotNull
    @Size(min = 2, max = 25)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 25)
    private String lastName;
    @NotNull(message = "email should be valid")
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "email should fit global standards")
    private String email;
    private String userName;
    @NotNull
    private String password;
    @Pattern(regexp = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$")
    private String phone;
    private Date created;
    private Date updated;
    private Boolean isEnabled;
    private Boolean isCredentialsNotExpired;
    private Boolean isAccountNonLocked;
    private Boolean isAccountNonExpired;
    private Set<UserRolesEntity> roles;


    public UserDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(phone, userDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, email, password, phone);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Set<UserRolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRolesEntity> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
