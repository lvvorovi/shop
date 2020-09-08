package com.shop.domains.userRoles;

import com.shop.domains.role.RoleEntity;
import com.shop.domains.users.UserEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class UserRolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    public RoleEntity role;

    public UserRolesEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesEntity userRoles = (UserRolesEntity) o;
        return id == userRoles.id &&
                Objects.equals(user, userRoles.user) &&
                Objects.equals(role, userRoles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, role);
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", user=" + user +
                ", roles=" + role +
                '}';
    }
}
