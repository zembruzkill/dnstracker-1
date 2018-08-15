package com.gslandtreter.dnstracer.common.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parameters")
public class ParametersEntity {
    private String key;
    private String value;

    @Id
    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParametersEntity that = (ParametersEntity) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, value);
    }
}
