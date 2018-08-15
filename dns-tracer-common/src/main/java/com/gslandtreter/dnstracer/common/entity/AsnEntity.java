package com.gslandtreter.dnstracer.common.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "asn")
public class AsnEntity {
    private int id;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AsnEntity asnEntity = (AsnEntity) o;
        return id == asnEntity.id &&
                Objects.equals(name, asnEntity.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
