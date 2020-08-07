package com.shop.domains.userItems;

import com.shop.domains.products.ProductEntity;
import com.shop.domains.users.UserEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_items")
public class UserItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @Column(name = "quantity")
    int quantity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserItemEntity productListEntity = (UserItemEntity) o;
        return quantity == productListEntity.quantity &&
                Objects.equals(id, productListEntity.id) &&
                Objects.equals(user, productListEntity.user) &&
                Objects.equals(product, productListEntity.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, product, quantity);
    }

    @Override
    public String toString() {
        return "UserItemEntity{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
