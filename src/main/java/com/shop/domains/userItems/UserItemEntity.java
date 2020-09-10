package com.shop.domains.userItems;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shop.domains.products.ProductEntity;
import com.shop.domains.users.UserEntity;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "user_items")
public class UserItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "updated")
    private Timestamp updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    public UserItemEntity() {
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

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserItemEntity that = (UserItemEntity) o;
        return quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(user.getId(), that.user.getId()) &&
                Objects.equals(product.getId(), that.product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user.getId(), product.getId(), quantity);
    }

    @Override
    public String toString() {
        return "UserItemEntity{" +
                "id=" + id +
                ", user_id =" + user.getId() +
                ", product_id =" + product.getId() +
                ", quantity=" + quantity +
                '}';
    }
}
